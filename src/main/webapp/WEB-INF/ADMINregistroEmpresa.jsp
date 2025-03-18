<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Halus | Añadir Empresa</title>
        <!--CSS-->
        <link rel="stylesheet" type="text/css" href="/css/style.css?version=0.5">
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
			      <li><a class="navlink montserrat" href="/admin/home">Inicio</a></li>
			      <li><a class="navlink montserrat" href="/admin/empresas">Empresas</a></li>
			      <li><a class="navlink montserrat" href="/admin/productos">Productos</a></li>
			      <li><a class="navlink montserrat" href="/admin/clientes">Clientes</a></li>
			      <li><a class="navlink montserrat" href="/admin/pedidos">Pedidos</a></li>
			      <li><a class="navlink montserrat" href="/admin/agregar/empresa">Agregar Empresa</a></li>
			      <li><a href="/logout" class="btn montserrat">Cerrar Sesión</a></li>
			</ul>
        </header>
        <main class="main">
            <h1 class="outfit m-1">Añadir Empresa</h1>
            <div class="r-cont">
                <form:form action="/admin/guardar/empresa" method="POST" modelAttribute="nuevaEmpresa">
                    <div class="mt-05">
                        <form:label class="montserrat" path="nombre">Nombre Empresa:</form:label>
                        <form:input path="nombre" class="txt-input montserrat" placeholder="Nombre de la empresa."/>
                        <form:errors path="nombre" class="txt-danger montserrat"/>
                    </div>
                    <div class="mt-05"><!-- En modelo el atributo es apellido, en vistas respecto a empresas será Nombre Representante Legal. -->
                        <form:label class="montserrat" path="apellido">Nombre Representante Legal:</form:label>
                        <form:input path="apellido" class="txt-input montserrat" placeholder="Nombre y apellido del representante legal de la empresa."/>
                        <form:errors path="apellido" class="txt-danger montserrat"/>
                    </div>
                    <div class="mt-05">
                        <form:label class="montserrat" path="email">Email:</form:label>
                        <form:input path="email" class="txt-input montserrat" placeholder="Email de la empresa."/>
                        <form:errors path="email" class="txt-danger montserrat"/>
                    </div>
                    <div class="mt-05">
                        <form:label path="direccion" class="montserrat">Dirección</form:label>
                        <form:input path="direccion" class="txt-input montserrat" placeholder="Dirección de la empresa."/>
                        <form:errors path="direccion" class="txt-danger montserrat" />
                    </div>
                    <div class="mt-05">
                        <form:label class="montserrat" path="password">Contraseña:</form:label>
                        <form:password path="password" class="txt-input montserrat"/>
                        <form:errors path="password" class="txt-danger montserrat"/>
                    </div>			
                    <div class="mt-05">
                        <form:label class="montserrat" path="confirmacion">Confirmar contraseña:</form:label>
                        <form:password path="confirmacion" class="txt-input montserrat"/>
                        <form:errors path="confirmacion" class="txt-danger montserrat"/>
                    </div>		
                    <button type="submit" class="btn montserrat mt-05">
                        <span> Registrar Empresa </span>
                    </button>
                </form:form>
            </div>
        </main>
        <footer>
            <div class="footer mt-3">
                <h5 class="outfit"> <i class="fa-regular fa-copyright"></i> Halus</h5>
                <p class="montserrat">Main font "Biko" <a href="http://www.jesuismonreve.org/biko-font-family/" target="_blank"> <i class="fa-regular fa-copyright"></i> Marco Ugolini </a></p>
                <p class="montserrat">Logo and design are property of this project.</p>
                <p class="montserrat">This site is prototype-only. Any images, fonts, texts, etc. are being used for this single prototype without any revenue. All rights to their respective owners.</p>
            </div>
        </footer>
    </body>
</html>