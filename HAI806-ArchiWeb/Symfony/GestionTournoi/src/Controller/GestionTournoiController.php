<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class GestionTournoiController extends AbstractController
{
    /**
     * @Route("/gestionTournois", name="app_gestion_tournoi")
     */
    public function index(): Response
    {
        return $this->render('gestion_tournoi/index.html.twig', [
            'controller_name' => 'GestionTournoiController',
        ]);
    }
}
