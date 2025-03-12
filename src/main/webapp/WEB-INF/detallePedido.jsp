<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalle pedido</title>
</head>
<body>
    <header class="head">
        <img src="/img/logo.webp" alt="Halus" class="logo-n" title="HALUS">
        <ul class="navbar">
            <li><a class="navlink montserrat" href="/home">Inicio</a></li>
            <li><a class="navlink montserrat" href="/cliente/pedidos">Mis Pedidos</a></li>
            <li><a class="navlink montserrat" href="/cliente/guardados">Guardados</a></li>
            <li><a class="navlink montserrat" href="/cliente/${usuarioEnSesion.id}">Perfil</a></li>
            <li><a href="/logout" class="btn montserrat">Cerrar Sesión</a></li>
       </ul>
    </header>
    <main class="main">
        <div class="card">
            <h1>Numero pedido</h1>
            <c:forEach var="pedido" items="${pedidos}">
                <p>${pedido.producto.nombre}</p>
                <p>${pedido.cantidad}</p> <!-- revisar -->
                <p>-----------</p>
            </c:forEach>
            <h5>TOTAL: ${}</h5>
            <div>
                <h6>Información cliente: </h6>
                <p>Nombre cliente ${usuarioEnSesion.nombre}</p>
                <p>Dirección cliente ${usuarioEnSesion.direccion}</p> <!-- revisar: modulo usuario sin direccion -->
                <h6>Información proveedor: </h6>
                <p>Nombre empresa ${producto.creador.nombre}</p>  <!-- revisar: no me acuerdo -->
                <p>Contacto empresa ${producto.creador.email}</p>
            </div>
            <button type="submit">Cancelar pedido</button>
        </div>
    </main>
    <footer>
        <div class="footer mt-3">
            <h5 class="outfit"> <i class="fa-regular fa-copyright"></i> Halus</h5>
            <p class="montserrat">Main font "Biko" <a href="http://www.jesuismonreve.org/biko-font-family/" target="_blank"> <i class="fa-regular fa-copyright"></i> Marco Ugolini </a></p>
            <p class="montserrat">Logo and design are property of this proyect.</p>
            <p class="montserrat">This site is prototype-only. Any images, fonts, texts, etc. are being used for this single prototype without any revenue. All rights to their respective owners.</p>
        </div>
    </footer>
</body>
</html>