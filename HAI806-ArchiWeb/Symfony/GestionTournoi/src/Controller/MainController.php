<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class MainController extends AbstractController
{
    /**
     * @Route("/", name="app_main")
     */
    public function index(): Response {
        return $this->render('main/index.html.twig', [
            'controller_name' => 'MainController',
        ]);
    }


    /**
    * @Route("/bonjour/{nom<[a-z]+>}/{id<\d+>?-1}")
    */
    public function bonjour($nom=null, $id=null) {
        if (isset($_GET["nom"])) {
            $nom = $_GET["nom"] ;
        }
        if (isset($_GET["id"])) {
            $id = $_GET["id"] ;
        }      

        if (!$nom){
            return new Response('Bonjour le monde !');
        } else {
           return new Response("Bonjour $nom d'id $id !");
        }
    }

    /**
    * @Route("/fact/{id<\d+>?2}")
    */
    public function fact($id=null) {
        if (isset($_GET["id"])) {
            $id = $_GET["id"] ;
        }

        $result = factorielle($id);
        return new Response("Factorielle de $id  =  $result");
    }

    /**
    * @Route("/comp/{a<\d+>?1}/{b<\d+>?2}")
    */
    public function comp($a=null, $b=null) {
        if (isset($_GET["a"])) {
            $a = $_GET["a"] ;
        }
        if (isset($_GET["b"])) {
            $b = $_GET["b"] ;
        }   

        $result = factorielle($a) / factorielle($b) * factorielle($a-$b);
        return new Response("C($a , $b) =  $result");
    }
}

function factorielle($n) {
    $result = 1;
    for ($i = $n; $i > 0; $i--) {
        $result = $result * $i;
    }
    return $result;
}

