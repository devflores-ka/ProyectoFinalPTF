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
        <link rel="stylesheet" type="text/css" href="/css/style.css">
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
				<li><a class="navlink montserrat" href="/cliente/home">Inicio</a></li>
		        <li><a class="navlink montserrat" href="/cliente/pedidos">Mis Pedidos</a></li>
		        <li><a class="navlink montserrat" href="/cliente/guardados">Guardados</a></li>
		        <li><a class="navlink montserrat" href="/cliente/${usuarioEnSesion.id}">Perfil</a></li>
		        <li><a href="/logout" class="btn montserrat">Cerrar Sesión</a></li>
		    </ul>
        </header>
        <main class="main">
            <h1 class="outfit">Mis Pedidos</h1>
            <c:forEach var="pedido" items="${pedidos}">
                <div class="card">
                    <img class="det-img" src="${pedido.productosEnPedido.urlImagen}" alt="Imagen del pedido"> 
                    <div class="detalles">
                        <h3 class="outfit">Pedido #${pedido.id}</h3>
                        <c:forEach items="${pedido.productosEnPedido}" var="producto">
                            <p class="montserrat g-p">Producto: ${producto.nombre}</p>
                            <p class="montserrat g-p">Cantidad: ${producto.cantidad}</p>
                        </c:forEach>
                        <p class="montserrat g-p">Tipo de pedido: ${pedido.tipoDeServicio}</p>
                        <hr>
                        <p class="outfit mb-1">TOTAL <i class="fa-solid fa-dollar-sign"></i> ${pedido.totalDelPedido} CLP</p>
                        <a href="/cliente/pedido/${pedido.id}" class="btn montserrat">Detalles</a>
                    </div>
                </div>
            </c:forEach>

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
