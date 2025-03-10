<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Halus</title>
        <!--CSS-->
        <link rel="stylesheet" type="text/css" href="/css/style.css?version=0.4.2">
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
            <a href="/"><img src="/img/logo.webp" alt="Halus" class="logo-n" title="HALUS"></a>
            <ul class="navbar">
                <li><a class="navlink montserrat" href="/#nosotros">Nosotros</a></li>
                <li><a class="navlink montserrat" href="/#soluciones">Soluciones</a></li>
                <li><a class="navlink montserrat" href="/#comoFunciona">Funcionamiento</a></li>
                <li><a class="navlink montserrat" href="/empresas">Empresas</a></li>
                <li><a href="/registro/formulario" class="btn montserrat">Registro clientes</a></li>
                <li><a href="/login" class="btn montserrat">Entrar</a></li>
            </ul>
        </header>
        <main class="main">
            <h1 class="outfit m-1">Iniciar Sesión</h1>
            <div class="r-cont">
                <form:form action="/iniciarSesion" method="POST" modelAttribute="loginUsuario">
                    <div class="mt-05">
                        <form:label class="montserrat" path="emailLogin">Email:</form:label>
                        <form:input path="emailLogin" class="txt-input montserrat"/>
                        <form:errors path="emailLogin" class="txt-danger montserrat"/>
                    </div>
                    <div class="mt-05">
                        <form:label class="montserrat" path="passwordLogin">Contraseña:</form:label>
                        <form:password path="passwordLogin" class="txt-input montserrat"/>
                        <form:errors path="passwordLogin" class="txt-danger montserrat"/>
                    </div>				
                    <button type="submit" class="btn montserrat mt-05">
                        <span class="q-w"> Iniciar Sesión </span>
                    </button>
                </form:form>
            </div>
        </main>
        <footer>
            <div class="footer mt-11">
                <h5 class="outfit"> <i class="fa-regular fa-copyright"></i> Halus</h5>
                <p class="montserrat">Main font "Biko" <a href="http://www.jesuismonreve.org/biko-font-family/" target="_blank"> <i class="fa-regular fa-copyright"></i> Marco Ugolini </a></p>
                <p class="montserrat">Logo and design are property of this proyect.</p>
                <p class="montserrat">This site is prototype-only. Any images, fonts, texts, etc. are being used for this single prototype without any revenue. All rights to their respective owners.</p>
            </div>
        </footer>
    </body>
</html>