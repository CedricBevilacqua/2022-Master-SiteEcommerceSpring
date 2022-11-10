# SRA1 - Projet JEE - Partie Clients

## Auteurs 
- BEVILACQUA Cedric
- GUFFROY Cyril
- LAGHMICH Manal
- LIARD Thomas
- NISON Jeremy

## Résumé du projet
L'objectif est de créer un site de drive e-commerce, dans lequel chaque groupe de la promo effectue une partie dudit drive. Notre partie porte sur les clients, et leurs commandes. Pour cela, un client doit se connecter, faire sa commande, et peut aussi voir son historique de commandes. Il a le droit de faire une réclamation, et de modifier ses informations personnelles. Enfin, il existe des comptes spéciaux, des comptes admin, pouvant gérer le bannissement et les réclamations des utilisateurs.

## Ce qui a été implémenté
Toutes les User Stories, hormis celle sur le changement en "ANSWRED" d'une réclamation, sont gérées.

## Installation du site web
Il faut :
- effectuer un `git clone` du projet
- aller dans le dossier `clients` et faire un ```mvn clean package war:war```
- faite pointer un contexte tomcat sur le .war généré dans le dossier `tagret` du projet. 
- Attention : les liens fournis ci-dessous fonctionnent si le path du contexte vaut `/clients`. Si le path est différent, le site fonctionne aussi mais il faut penser à le modifier dans les liens ci dessous.
- Lancer Tomcat et accéder au site web

## Comment accéder au site web ?
Il suffit d'aller sur l'endpoint `/clients/auth/login.html`. Ainsi, sur un serveur local, l'adresse de base est `localhost:8080/clients/auth/login.html`.

## Liste des endpoints possibles :
`clients/auth/login.html` : login  
`clients/registration/signup.html` : inscription  
`clients/mock/addMocks.data` : ajout de clients *mockés*  
`clients/catalog/categories.html` : catalogue de base  
`clients/catalog/category/{id}.html` : catégorie id  
`clients/cart/content.html` : contenu du panier  
`clients/orders/validate.html` : validation de la commande  
`clients/orders/history.html` : historique de la commande  
`clients/account/edit.html` : modification de compte  
`clients/reclamation/page.html` : page d'index des réclamations  
`clients/reclamation/listReclamations/{id}.html` : liste des réclamations du client id  
`clients/reclamation/create.html` : création d'une réclamation  
`clients/reclamation/{id}.html` : réclamation id  
`clients/reclamation/{Id}/message` : envoi d'un message à la réclamation id  
`clients/admin/page.html` : page d'administration  
`clients/admin/listReclamations.html` liste des réclamations en WAITING  
`clients/admin/listUsers.html` : liste des utilisateurs  
`clients/admin/ban/{id}.data` : bannir le client id  
`clients/admin/unban/{id}.data` : débannir le client id  

## Pour tester le'activation du compte par mail
Pour faciliter les tests de l'activation du compte par mail, nous avons mis la date d'expiration des liens à 1min. Elle est modifiale dans le fichier : [VerificationToken](/clients/src/main/java/sra1/jee/projet/drive/model/VerificationToken.java)

## Difficultés rencontrées 
- L'une des difficultés principales fut l'implémentation du mail sender, car nous ne l'avions pas vu en TP, ni en cours, et que Sendgrid ne fonctionne à la fac qu'avec le SMTP de l'Université. Pour cela, nous n'utiliserons pas le VPN de l'université pour la présentation.
- Le temps : le projet semble très chronophage pour certains groupes, alors que d'autres ont des parties plus courtes
- Le changement dans les consignes : nous n'avons eu l'information du diapo que très tardivement, car il semble y avoir eu une confusion entre les enseignants. Cela nous a ajouté un stress plus important, mais nous nous adaptons à la situation et la comprenons

## Choix particuliers
- Nous avons décidé d'envoyer l'id et le rôle de l'utilisateur en session pour faciliter nos commandes EL/JSTL
- Nous avons choisi de créer quelques répertoires dans le dossier *views* afin de faciliter la lecture lors de la correction, et cela a rendu une partie du code plus lisible dans l'architecture du projet
- Si un utilisateur non admin accède à la page admin, il arrivera sur une page Error qui ne contient pas d'autre information. Nous aurions dû la continuer mais le manque de temps nous en a empêché
- Un dossier SQL existe, contenant des scripts SQL, car nous avions initialement créé une base SQLite avant de voir qu'il fallait obligatoirement passer par H2, MySQL ou PostgreSQL
