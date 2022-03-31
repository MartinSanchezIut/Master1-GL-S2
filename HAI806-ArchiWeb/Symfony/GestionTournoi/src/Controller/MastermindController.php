<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Session\SessionInterface;


class MastermindController extends AbstractController {

    /**
    * @Route("/mastermind", name="app_master_mind")
    */
    public function index(SessionInterface $session): Response {
        if(!$session->has('mot')){
            $session->set('mot', rand(0, 9) *1000 + rand(0, 9) *100 + rand(0, 9) *10 + rand(0, 9) );
        }
        if(!$session->has('hasWon')){
            $session->set('hasWon', false );
        }
        if(!$session->has('list')){
            $session->set('list', array() );
        }

        print_r($_POST); print("\n") ;
        print_r($session->get('mot',null) ); 
        print_r($session->get('hasWon',null) );
        print_r($session->get('list',null));


        if (isset($_POST['mot'])) { // Ajouter un essai
            if ($_POST['mot'] == $session->get('mot',null) ) {
                $session->set('hasWon', true );
            }else {
                $thelist = $session->get('list',[]);
                $lemot =  $_POST['mot'];
                array_push($thelist, $lemot);
            }
        }
        
        return $this->render('mastermind/index.html.twig', [
            'mot_deviner' => $session->get('mot',null),
            'hasWon' => $session->get('hasWon',null),
            'list' => $session->get('list',[]),
        ]);
    }


    /**
    * @Route("/mastermind/sessionClear")
    */
    function sessionClear(SessionInterface $session) : Response {
        $session->clear();
        return new Response("Session effacée");
    }
}