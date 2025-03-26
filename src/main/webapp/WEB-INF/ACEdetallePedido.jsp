<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Halus | Detalle del pedido</title>
        <!--CSS-->
        <link rel="stylesheet" type="text/css" href="/css/style.css?version=0.4.8">
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
            <c:choose>
            	<c:when test="${usuarioEnSesion.tipoDeUsuario == admin }">
            		<ul class="navbar">
				        <li><a class="navlink montserrat" href="/admin/home">Inicio</a></li>
				        <li><a class="navlink montserrat" href="/admin/empresas">Empresas</a></li>
				        <li><a class="navlink montserrat" href="/admin/productos">Productos</a></li>
				        <li><a class="navlink montserrat" href="/admin/clientes">Clientes</a></li>
				        <li><a class="navlink montserrat" href="/admin/pedidos">Pedidos</a></li>
				        <li><a class="navlink montserrat" href="/admin/agregar/empresa">Agregar Empresa</a></li>
				        <li><a href="/logout" class="btn montserrat">Cerrar Sesión</a></li>
				   	</ul>
			    </c:when>
			    <c:when test="${usuarioEnSesion.tipoDeUsuario == cliente }">
				    <ul class="navbar">
						<li><a class="navlink montserrat" href="/cliente/home">Inicio</a></li>
				        <li><a class="navlink montserrat" href="/cliente/pedidos">Mis Pedidos</a></li>
				        <li><a class="navlink montserrat" href="/cliente/carrito">Carrito</a></li>
				        <li><a class="navlink montserrat" href="/cliente/${usuarioEnSesion.id}">Perfil</a></li>
				        <li><a href="/logout" class="btn montserrat">Cerrar Sesión</a></li>
				    </ul>
			    </c:when>
			    <c:otherwise>
			    	<ul class="navbar">
			          <li><a class="navlink montserrat" href="/empresa/home">Inicio</a></li>
			          <li><a class="navlink montserrat" href="/empresa/productos">Mis productos</a></li>
			          <li><a class="navlink montserrat" href="/empresa/nuevo/producto">Agregar Producto</a></li>
			          <li><a class="navlink montserrat" href="/empresa/ventas">Mis ventas</a></li>
			          <li><a class="navlink montserrat" href="/empresa/${usuarioEnSesion.id}">Mi empresa</a></li>
			          <li><a href="/logout" class="btn montserrat">Cerrar Sesión</a></li>
		            </ul>
			    </c:otherwise>
            </c:choose> 
    </header>
    <main class="main">
        <div id="pedido">
            <h1 class="outfit">Pedido #${pedido.id}</h1>
            <h2 class="outfit">Productos: <i class="fa-regular fa-circle-question g-p" title="Los productos tachados no pertenecen a tu empresa"></i></h2>
            <br>
            <c:forEach var="pEp" items="${pEp}">
            	<c:choose>
	                <c:when test="${pEp.producto.creador.id == usuarioEnSesion.id}">
	                	 <h3 class="outfit ">${pEp.producto.nombre}</h3>
	                	 <h3 class="outfit i">Valor: ${pEp.producto.pVenta} CLP</h3>
	                	 <h3 class="montserrat i">Proveedor: Mi empresa (${pEp.producto.creador.nombre})</h3>
	                </c:when>
	                <c:otherwise>
	                	<p class="montserrat i g-p txt-lt">${pEp.producto.nombre}</p>
	                	<p class="montserrat i g-p txt-lt">Proveedor: ${pEp.producto.creador.nombre }</p>
	                </c:otherwise>
	            </c:choose>              
                <hr class="mt-05">
            </c:forEach> 
            	<br>
                <h2 class="outfit">Información cliente: </h2>
                <p class="montserrat g-p i">Nombre cliente: ${pedido.creador.nombre} ${pedido.creador.apellido}</p>
                <p class="montserrat g-p i">Dirección cliente: ${pedido.creador.direccion}</p> 
                <hr class="mt-05">
            <h3 class="outfit g-p"> Tipo de pedido: ${pedido.tipoDeServicio }</h3>
            <h3 class="outfit mb-1">TOTAL: ${pedido.totalDelPedido} CLP</h3>
            <c:choose>
            	<c:when test="${usuarioEnSesion.tipoDeUsuario == cliente }">
            		<a href="/cliente/pedidos" class=" btn montserrat"> <i class="fa-solid fa-chevron-left"></i> Volver </a>
            		<form action="/cliente/borrar/pedido/${pedido.id}" method="POST" class="mt-05">
               			<input type="hidden" name="_method" value="DELETE">
                		<button type="submit" class="btn-danger montserrat mt-05 btn-full">
                    		<span> <i class="fa-solid fa-xmark"></i> Cancelar pedido </span>
                		</button>
            		</form> 
            	</c:when>
            	<c:when test="${usuarioEnSesion.tipoDeUsuario == admin }">
            		<form action="/admin/borrar/pedido/${pedido.id}" method="POST" class="mt-05">
               			<input type="hidden" name="_method" value="DELETE">
                		<button type="submit" class="btn-danger montserrat mt-05 btn-full">
                    		<span> <i class="fa-solid fa-xmark"></i> Cancelar pedido </span>
                		</button>
            		</form> 
            	</c:when>
            	<c:otherwise>
            		<form action="/empresa/borrar/pedido/${pedido.id}" method="POST" class="mt-05">
               			<input type="hidden" name="_method" value="DELETE">
                		<button type="submit" class="btn-danger montserrat mt-05 btn-full">
                    		<span> <i class="fa-solid fa-xmark"></i> Cancelar pedido </span>
                		</button>
            		</form> 
            	</c:otherwise>
            </c:choose>
        </div>
    </main>
    <footer>
        <div class="footer">
            <h5 class="outfit"> <i class="fa-regular fa-copyright"></i> Halus</h5>
            <p class="montserrat">Main font "Biko" <a href="http://www.jesuismonreve.org/biko-font-family/" target="_blank"> <i class="fa-regular fa-copyright"></i> Marco Ugolini </a></p>
            <p class="montserrat">Logo and design are property of this project.</p>
            <p class="montserrat">This site is prototype-only. Any images, fonts, texts, etc. are being used for this single prototype without any revenue. All rights to their respective owners.</p>
        </div>
    </footer>
</body>
</html>