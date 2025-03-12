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
        <header>
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
        </header>
        <main class="main">
            <h1>Añadir Empresa</h1>
            <div class="card">
                <form:form action="/nuevaEmpresa" method="post" modelAttribute="nuevaEmpresa">
                    <div>
                        <form:label path="nombre" class="mt-3">Nombre</form:label>
                        <form:input path="nombre" class="form-control mt-2" />
                        <form:errors path="nombre" class="text-danger" />
                    </div>
                    <div> <!-- revisar: no representante legal en modelo. PD: acabo de leer lo que escribieron en whatsapp-->
                        <form:label path="apellido" class="mt-3">Nombre Representante legal</form:label>  
                        <form:input path="apellido" class="form-control mt-2" />
                        <form:errors path="apellido" class="text-danger" />
                    </div>
                    <div>
                        <form:label path="email" class="mt-3">Correo</form:label>
                        <form:input path="email" class="form-control mt-2" />
                        <form:errors path="email" class="text-danger" />
                    </div>
                    <div>
                        <form:label path="direccion" class="mt-3">Dirección</form:label>
                        <form:input path="direccion" class="form-control mt-2" />
                        <form:errors path="direccion" class="text-danger" />
                    </div>
                    <div>
                        <form:label path="password" class="mt-3">Contraseña</form:label>
                        <form:password path="password" class="form-control mt-2" />
                        <form:errors path="password" class="text-danger" />
                    </div>
                    <div>
                        <form:label path="confirmacion" class="mt-3">Confirmar Contraseña</form:label>
                        <form:password path="confirmacion" class="form-control mt-2" />
                        <form:errors path="confirmacion" class="text-danger" />
                    </div>
                    <input type="submit" class="btn" value="Registrar Empresa">
                </form:form>
            </div>
        </main>    
        <footer>
            <div class="footer">
                <h5 class="outfit"> <i class="fa-regular fa-copyright"></i> Halus</h5>
                <p class="montserrat">Main font "Biko" <a href="http://www.jesuismonreve.org/biko-font-family/"> <i class="fa-regular fa-copyright"></i> Marco Ugolini </a></p>
                <p class="montserrat">Logo and design are property of this proyect.</p>
                <p class="montserrat">This site is prototype-only. Any images, fonts, texts, etc. are being used for this single prototype without any revenue. All rights to their respective owners.</p>
            </div>
        </footer>
    </body>
</html>