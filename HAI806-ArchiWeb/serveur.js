const express = require('express');
const app     = express();
app.use(express.json());
app.use(express.urlencoded({extended:true}));
app.use(function (req, res, next) {
    res.setHeader('Access-Control-Allow-Headers', '*');
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE');
    res.setHeader('Content-type', 'application/json');
    next();
});

const MongoClient = require('mongodb').MongoClient;
const ObjectID    = require('mongodb').ObjectId;
const url         = "mongodb://localhost:27017";

MongoClient.connect(url, {useNewUrlParser: true}, (err, client) => {
    let db = client.db("NomDATABASE");

    console.log("Démarage du serveur node");
    console.log("avis | ingredients | recettes | users | likes");

    /* Lire n'importe quelle table */
    app.get("/:tab", (req,res) => {
        let tab = req.params.tab ;
        console.log("/" + tab)
        ;
        try {
            db.collection(tab).find().toArray((err, documents) => {
                res.end(JSON.stringify(documents));
            });
        } catch(e) {
            console.log("Erreur sur /" + tab + " : " + e);
            res.end(JSON.stringify([]));
        }
    });

   /* Lire dans n'importe quelle table, les objets ayant un ID egal a */
    app.get("/:tab/_id/:valeur", (req,res) => {
            let tab = req.params.tab ;
            let val = req.params.valeur;

            console.log("/" + tab + "/_id" + "/" + val);

            try {
                let s = {"_id": ObjectID(val)} ;
                returnvalue = [] ;
                
                db.collection(tab).find(s).toArray((err, documents) => {
                    for (let doc of documents) {
                        returnvalue.push([doc]); 
                    }
                    res.end(JSON.stringify(returnvalue));
		});
            } catch(e) {
                console.log("Erreur sur /"+ tab + "/_id" + "/" + val +" : "+ e);
                res.end(JSON.stringify([]));
            }
        });


    /* Lire dans n'importe quelle table, les objets ayant une attribut egal a une valeur */
    app.get("/:tab/:attribut/:valeur", (req,res) => {
            let tab = req.params.tab ;
            let att = req.params.attribut;
            let val = req.params.valeur;

            console.log("/" + tab + "/"+ att + "/" + val);

            try {
                let s = {} ;
                returnvalue = [] ;
                db.collection(tab).find(s).toArray((err, documents) => {
                    for (let doc of documents) {
                        if (doc.hasOwnProperty(att) && doc[att] === val) {
                            if (!returnvalue.includes(doc.att)) {
                                returnvalue.push([doc]); 
                            }
                        }
                    }
                    res.end(JSON.stringify(returnvalue));
                });
            } catch(e) {
                console.log("Erreur sur /"+ tab + "/"+ att + "/" + val +" : "+ e);
                res.end(JSON.stringify([]));
            }
        });




    /* Connexion */
    // Pour tester : curl --data "pseudo=Martin&password=temalatailledurat" http://localhost:8888/user/connexion
    app.post("/user/connexion", (req,res) => {
        console.log("/user/connexion avec "+JSON.stringify(req.body));
        try {
            if ( (req.body['password'] !== undefined) && ((req.body['email'] !== undefined) || (req.body['pseudo'] !== undefined)) ) {
                 	
		    db.collection("users")
		    .find(req.body)
		    .toArray((err, documents) => {
		        if (documents.length == 1) {
		           documents[0]["password"] = "**********";
		           res.end(JSON.stringify({"resultat": 1, "message": "Authentification réussie", "user": documents[0] }));
		           
		           
		        } else { 
		        	res.end(JSON.stringify({"resultat": 0, "message": "Email et/ou mot de passe incorrect"}));	
		        }
		    });
            }else {
		res.end(JSON.stringify({"resultat": 0, "message": "Elements manquant pour la connexion"}));
            }
        } catch (e) {
            res.end(JSON.stringify({"resultat": 0, "message": e}));
        }
    });
    
    /* Creation d'un compte */
    // Pour tester : curl --data "pseudo=user&email=user@localhost&password=123" http://localhost:8888/user/inscription
    app.post("/user/inscription", (req,res) => {
        console.log("/user/inscription avec "+JSON.stringify(req.body));
        try {
            db.collection("users").insertOne(req.body);
            res.end(JSON.stringify({"resultat": 1, "message": "Inscription réussie"}));
        } catch (e) {
            res.end(JSON.stringify({"resultat": 0, "message": e}));
        }
    });
    
    
    
    /* Creation d'un avis */
    // Pour tester : curl --data "id_recette=iddelarecette&id_auteur=iddelauteur&pseudo_auteur=ajout&date=01/01/01&avis=Ajoutd'unavis" http://localhost:8888/avis/add
    app.post("/avis/add", (req,res) => {
        console.log("/avis/add avec "+JSON.stringify(req.body));
        try {
            db.collection("avis").insertOne(req.body);
            res.end(JSON.stringify({"resultat": 1, "message": "Ajout de l'avis réussie"}));
        } catch (e) {
            res.end(JSON.stringify({"resultat": 0, "message": e}));
        }
    });   
    
    
        /* Creation d'un like */
    // Pour tester : curl --data "id_likeditem=testtest&id_wholiked=nanani" http://localhost:8888/likes/add
    app.post("/likes/add", (req,res) => {
        console.log("/likes/add avec "+JSON.stringify(req.body));
        try {
            db.collection("likes").insertOne(req.body);
            res.end(JSON.stringify({"resultat": 1, "message": "Ajout du like réussi"}));
        } catch (e) {
            res.end(JSON.stringify({"resultat": 0, "message": e}));
        }
    });   
    
    
        /* Creation d'une recette */
    // Pour tester : curl --data "id_recette=iddelarecette&id_auteur=iddelauteur&pseudo_auteur=ajout&date=01/01/01&avis=Ajoutd'unavis" http://localhost:8888/recettes/add
    app.post("/recettes/add", (req,res) => {
        console.log("/recettes/add avec "+JSON.stringify(req.body));
        try {
            db.collection("recettes").insertOne(req.body);
            res.end(JSON.stringify({"resultat": 1, "message": "Ajout de la recette réussite"}));
        } catch (e) {
            res.end(JSON.stringify({"resultat": 0, "message": e}));
        }
    });     
    
});

// Port d'écoute
app.listen(8888);
