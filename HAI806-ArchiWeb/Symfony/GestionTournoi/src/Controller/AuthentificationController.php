<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\HttpFoundation\RedirectResponse;

class AuthentificationController extends AbstractController {

    /**
     * @Route("/auth", name="app_authentification")
     */
    public function index(SessionInterface $session): Response {
        if(!$session->has('user')){
            return $this->login() ;
        }
        return $this->render('authentification/index.html.twig', [
            'user_name' => $session->get('user', null),
        ]);
    }

    /**
    * @Route("/auth/disconnect", name="auth_disconnect")
    */
    function disconnect(SessionInterface $session) : RedirectResponse {
        $session->remove('user');
        // echo "<script type='text/javascript'>alert('Déconnection');</script>";

        return $this->redirectToRoute('app_authentification');
    }

    /**
    * @Route("/auth/connect", name="auth_connect")
    */
    function connect(SessionInterface $session, $user='none'): RedirectResponse {
        if (isset($_POST['user'])) {
            $user = $_POST['user'] ;
        }
        $session->set('user', $user);
        // echo "<script type='text/javascript'>alert('Connecté');</script>";

        return $this->redirectToRoute('app_authentification');
    }  



    /**
    * @Route("/auth/login", name="auth_login")
    */
    public function login(): Response {
        return $this->render('authentification/login.html.twig');
    }


    /**
    * @Route("/auth/register", name="auth_register")
    */
    public function register(): Response {
        return $this->render('authentification/register.html.twig');
    }
}
