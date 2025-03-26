<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Halus | Calculadora </title>
        <!--CSS-->
        <link rel="stylesheet" type="text/css" href="/css/style.css?version=0.4.5">
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
	                <c:when test="${usuarioEnSesion == null }">
	                        <ul class="navbar">
	                            <li><a class="navlink montserrat" href="/#nosotros">Nosotros</a></li>
	                            <li><a class="navlink montserrat" href="/#soluciones">Soluciones</a></li>
	                            <li><a class="navlink montserrat" href="/#comoFunciona">Funcionamiento</a></li>
                                <li><a class="navlink montserrat" href="/informacion"><i class="fa-solid fa-plus"></i> info</a></li>
                                <li><a class="navlink montserrat" href="/empresas">Empresas</a></li>
                                <li><a href="/registro/formulario" class="btn montserrat">Registro clientes</a></li>
                                <li><a href="/login" class="btn montserrat">Entrar</a></li>
                            </ul>
                    </c:when>
                    <c:when test="${usuarioEnSesion.tipoDeUsuario == admin }">
                        <ul class="navbar">
                            <li><a class="navlink montserrat" href="/#nosotros">Nosotros</a></li>
                            <li><a class="navlink montserrat" href="/#soluciones">Soluciones</a></li>
                            <li><a class="navlink montserrat" href="/#comoFunciona">Funcionamiento</a></li>
                            <li><a class="navlink montserrat" href="/informacion"><i class="fa-solid fa-plus"></i> info</a></li>
                            <li><a class="navlink montserrat" href="/empresas">Empresas</a></li>
                            <li><a class="navlink montserrat" href="/admin/home">Home</a></li>
                            <li><a href="/logout" class="btn montserrat">Cerrar Sesión</a></li>
                        </ul>
                    </c:when>
                    <c:when test="${usuarioEnSesion.tipoDeUsuario == cliente }">
                        <ul class="navbar">
                            <li><a class="navlink montserrat" href="/#nosotros">Nosotros</a></li>
                            <li><a class="navlink montserrat" href="/#soluciones">Soluciones</a></li>
                            <li><a class="navlink montserrat" href="/#comoFunciona">Funcionamiento</a></li>
                            <li><a class="navlink montserrat" href="/informacion"><i class="fa-solid fa-plus"></i> info</a></li>
                            <li><a class="navlink montserrat" href="/empresas">Empresas</a></li>
                            <li><a class="navlink montserrat" href="/cliente/home">Home</a></li>
                            <li><a href="/logout" class="btn montserrat">Cerrar Sesión</a></li>
                        </ul>
                    </c:when>                  
                    <c:otherwise>
                        <ul class="navbar">
                            <li><a class="navlink montserrat" href="/#nosotros">Nosotros</a></li>
                            <li><a class="navlink montserrat" href="/#soluciones">Soluciones</a></li>
                            <li><a class="navlink montserrat" href="/#comoFunciona">Funcionamiento</a></li>
                            <li><a class="navlink montserrat" href="/informacion"><i class="fa-solid fa-plus"></i> info</a></li>
                            <li><a class="navlink montserrat" href="/empresas">Empresas</a></li>
                            <li><a class="navlink montserrat" href="/empresa/home">Home</a></li>
                            <li><a href="/logout" class="btn montserrat">Cerrar Sesión</a></li>
                        </ul>
                    </c:otherwise>
                </c:choose>
        </header>
        <main class="main">
            <section class="cont-sect mb-5" id="info">
                <h2 class="outfit h2-o">¿Porqué energía solar?</h2>
                <h2 class="outfit">"La energía solar está destinada a ser la mayor fuente de energía renovable para el 2029."</h2>
	            <p class="montserrat  w-txt">Beneficiándose de políticas que la apoyan, el costo de la energía generada de manera <em>Fotovoltaica</em> se ha desplomado dramáticamente en las últimas décadas, contribuyendo a un <em>boom</em> en el desarrollo y despliegue de paneles solares, con una capacidad global que crece a un paso histórico. Entre los años 2018 hasta el 2023, se triplicó. <a target="_blank" href="https://www.iea.org/energy-system/renewables/solar-pv"><sup>[1]</sup></a></p>
	            <p class="montserrat w-txt">Entre los siguientes años se espera que el uso de la tecnología fotovoltaica aumente hasta abarcar un 80% del uso entre todas las opciones de energías renovables - ¡Esto es bueno! Significa que cada vez se espera que compañías y hogares como el de cada uno de nosotros implemente estas tecnologías, contribuyendo al cuidado de nuestro medioambiente directamente disminuyendo la emisión de gases que emite la generación de electricidad convencional.</p>
	            <p class="montserrat w-txt">La instalación fotovoltaica genera un <strong>ahorro</strong> de emisiones de CO2. Con una producción anual de 53 MWh (53000 kWh) de electricidad solar, se pueden evitar las emisiones asociadas a la electricidad convencional generada a partir de combustibles fósiles, la cual genera aproximadamente 450-900 gramos de CO2 por kWh.<a target="_blank" href="http://hdl.handle.net/2117/420908" title="pag.53"><sup>[2]</sup></a></p>
	           	<a href="https://www.iea.org/data-and-statistics/charts/share-of-renewable-electricity-generation-by-technology-2000-2030" target="_blank"><img alt="Gráfico" src="/img/info.png"></a>
            </section>
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
