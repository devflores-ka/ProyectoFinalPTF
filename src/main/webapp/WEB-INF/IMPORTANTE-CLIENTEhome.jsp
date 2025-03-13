<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Halus</title>
        <!--CSS-->
        <link rel="stylesheet" type="text/css" href="/css/style.css?version=0.2">
        <!--FAFA-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!--FONT FAMILY@Outfit/@Montserrat-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Outfit:wght@100..900&display=swap" rel="stylesheet">
        <!--FAVICON-->
        <link rel="icon" type="image/x-icon" href="/img/icon.webp">
    </head>
    <body>
        <header class="head">
            <img src="/img/logo.webp" alt="Halus" class="logo-n" title="HALUS">
            <ul class="navbar">
				<li><a class="navlink montserrat" href="/cliente/home">Inicio</a></li>
		        <li><a class="navlink montserrat" href="/cliente/pedidos">Mis Pedidos</a></li>
		        <li><a class="navlink montserrat" href="/cliente/guardados">Guardados</a></li>
		        <li><a class="navlink montserrat" href="/cliente/${usuarioEnSesion.id}">Perfil</a></li>
		        <li><a href="/logout" class="btn montserrat">Cerrar Sesión</a></li>
		   </ul>
        </header>
        <main id="main">
            <div id="c-header">
                <img src="/img/header-image.webp" class="c-img">
                <div id="c-header-txt">
                    <h2 class="outfit">Tienda</h2>
                    <p class="montserrat mb-1">Descubre las opciones de la energia sustentable</p>
                    <a href="/cliente/todo" class="btn montserrat">Tienda</a>
                </div>
            </div>
            <div class="c-cont">
                <img src="/img/eolico-solar.webp" class="flex-2">
                <div class="flex-4 c-div-h">
                    <h3 class="outfit">Leasing Solar</h3>
                    <p class="montserrat mb-1">Paga una cuota mensual por usar la instalación sin comprarla.</p>
                    <a href="/cliente/arrendar" class="btn montserrat">Contrata</a>
                </div>
            </div>
            <div class="c-cont">
                <img src="/img/panel.webp" class="flex-2">
                <div class="flex-4 c-div-h">
                    <h3 class="outfit">Marketplace</h3>
                    <p class="montserrat mb-1">Encuentra todo lo que necesitas para comenzar con la energía sustentable.</p>
                    <a href="/cliente/comprar" class="btn montserrat">Comprar</a>
                </div>
            </div>
        </main>    
        <footer>
            <div class="footer mt-4">
                <h5 class="outfit"> <i class="fa-regular fa-copyright"></i> Halus</h5>
                <p class="montserrat">Main font "Biko" <a href="http://www.jesuismonreve.org/biko-font-family/"> <i class="fa-regular fa-copyright"></i> Marco Ugolini </a></p>
                <p class="montserrat">Logo and design are property of this proyect.</p>
                <p class="montserrat">This site is prototype-only. Any images, fonts, texts, etc. are being used for this single prototype without any revenue. All rights to their respective owners.</p>
            </div>
        </footer>
    </body>
</html>