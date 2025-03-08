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
        <link rel="stylesheet" type="text/css" href="/css/style.css?version=0.1">
        <!--FAFA-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!--FONT FAMILY@Outfit/@Montserrat-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Outfit:wght@100..900&display=swap" rel="stylesheet">
        <!--FAVICON-->
        <link rel="icon" type="image/x-icon" href="img/icon.webp">
    </head>
    <body>
        <header class="head">
            <img src="img/logo.webp" alt="Halus">
            <ul>
                <li><a href="#nosotros">Nosotros</a></li>
                <li><a href="#soluciones">Soluciones</a></li>
                <li><a href="#comoFunciona">Funcionamiento</a></li>
                <li><a href="#">Empresas</a></li>
                <li><a href="#contacto">Contacto</a></li>
                <li><a href="#" class="btn">Registro clientes</a></li>
                <li><a href="#" class="btn"> <i class="fa-solid fa-arrow-right-to-bracket"></i> Entrar</a></li>
            </ul>
        </header>
        <h1>Iniciar Sesión</h1>
        <main class="r-cont">
            <form:form action="iniciarSesion" method="POST" modelAttribute="LoginUsuario">
				<div class="mt-05">
					<form:label path="emailLogin"><i class="fa-solid fa-at"> </i> Email:</form:label>
					<form:input path="emailLogin" class="txt-input"/>
					<form:errors path="emailLogin" class="text-danger"/>
				</div>
				<div class="mt-05">
					<form:label path="passwordLogin"><i class="fa-solid fa-lock"> </i> Contraseña:</form:label>
					<form:password path="passwordLogin" class="txt-input"/>
					<form:errors path="passwordLogin" class="text-danger"/>
				</div>
				<button type="submit" class="">
					<i class="fa-solid fa-user-check" id="signUp"></i><span class="q-w"> Iniciar Sesión </span>
				</button>
			</form:form>
        </main>
    </body>
</html>