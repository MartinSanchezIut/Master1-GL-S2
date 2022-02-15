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
    console.log("avis | produits | recettes | users");

    /* Liste détaillé des produits */
    app.get("/produits", (req,res) => {
        console.log("/produits");
        try {
            db.collection("produits").find().toArray((err, documents) => {
                res.end(JSON.stringify(documents));
            });
        } catch(e) {
            console.log("Erreur sur /produits : " + e);
            res.end(JSON.stringify([]));
        }
    });
    /* Détail d'un produit */
    app.get("/produits/:nomProduit", (req,res) => {
	let nomProduit = req.params.nomProduit;
        console.log("/produits/"+nomProduit);
        try {
            db.collection("produits").find({nom:nomProduit}).toArray((err, documents) => {
                res.end(JSON.stringify(documents));
            });
        } catch(e) {
            console.log("Erreur sur /produits/"+nomProduit+" : "+ e);
            res.end(JSON.stringify([]));
        }
    });





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
        console.log("/utilisateurs/connexion avec "+JSON.stringify(req.body));
        try {
            db.collection("users")
            .find(req.body)
            .toArray((err, documents) => {
                if (documents.length == 1)
                    res.end(JSON.stringify({"resultat": 1, "message": "Authentification réussie"}));
                else res.end(JSON.stringify({"resultat": 0, "message": "Email et/ou mot de passe incorrect"}));
            });
        } catch (e) {
            res.end(JSON.stringify({"resultat": 0, "message": e}));
        }
    });
});

// Port d'écoute
app.listen(8888);
