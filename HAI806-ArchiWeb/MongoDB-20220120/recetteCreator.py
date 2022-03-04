#!/usr/bin/env python3


if __name__ == '__main__':
    print("\n\n")
    print("Outil de creation de recette => JSON")
    print("\n\n")

    value = "{"

    nom = input("\nNom de la recette : ")
    value += '"nom":"{0}", \n'.format(nom)

    tps_prep = input("\nTemps de préparation de la recette : ")
    value += '"tps_prep":"{0}", \n'.format(tps_prep)

    difficulte = input("\nDifficulte de la recette : ")
    value += '"difficulte":"{0}", \n'.format(difficulte)

    mode_prep = input("\nMode de préparation de la recette : ")
    value += '"mode_prep":"{0}", \n'.format(mode_prep)

    nb_pers = input("\nNombre de personnes de la recette : ")
    value += '"nb_pers":"{0}", \n'.format(nb_pers)

   
    id_auteur = input("\nId auteur de la recette : ")
    value += '"id_auteur":"{0}", \n'.format(id_auteur)

    pseudo_auteur = input("\nPseudo auteur de la recette : ")
    value += '"pseudo_auteur":"{0}", \n'.format(pseudo_auteur)

    date = input("\nDate de la recette : ")
    value += '"date":"{0}", \n'.format(date)

    value += '"ingredients":[ \n'
    while(input("\nAjouter un ingredient ? (0/1) : ") != "0"):
        ingredient = input("Nom de l'ingredient : ")
        quantite = input("Quantite de l'ingredient : ")
        unite = input("unite de la quantite : ")
        value += '{'
        value += '"nom":"{0}", "quantite":"{1}", "unite":"{2}"'.format(ingredient, quantite, unite)
        value += '}, \n'
    
    value += '],'


    value += '"etapes":[ \n'
    while(input("\nAjouter une etape ? (0/1) : ") != "0"):
        etape = input("Nom de l'etape : ")
        value += '["{0}"], \n'.format(etape)

    value += ']\n'

    print("\n\n")
    print(value + "},")
    print("\n\n")
