<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Halus</title>
        <!--CSS-->
        <link rel="stylesheet" type="text/css" href="/css/style.css?version=0.4">
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
        <header class="head" id="start">
            <a href="/"><img src="/img/logo.webp" alt="Halus" class="logo-n" title="HALUS"></a>
            <ul class="navbar" >
                <li><a class="navlink montserrat" href="#nosotros">Nosotros</a></li>
                <li><a class="navlink montserrat" href="#soluciones">Soluciones</a></li>
                <li><a class="navlink montserrat" href="#comoFunciona">Funcionamiento</a></li>
                <li><a class="navlink montserrat" href="/informacion">¿Porqué energía solar?</a></li>
                <li><a class="navlink montserrat" href="/empresas">Empresas</a></li>
                <li><a href="/registro/formulario" class="btn montserrat">Registro clientes</a></li>
                <li><a href="/login" class="btn montserrat">Entrar</a></li>
            </ul>
        </header>
        <main class="main">
        	<a href="#start" >
                <div id="to-top" class="btn">
                    <i class="fa-solid fa-arrow-up"></i>
                </div>
            </a>
            <div class="intro">
                <h2 class="outfit">Conéctate con la energía que no te falla.</h2>
                <p class="outfit l-txt">Encuentra proveedores, gestiona tus pedidos y haz crecer tu impacto en la transición solar.</p>
                <p class="sm-txt montserrat" id="intro-em">¿Eres una empresa?</p>
                <a href="/empresas" class="btn montserrat">Únete</a>
            </div>
            <section class="cont-slider">
                <div class="o-black">
                    <p class="outfit">Sostiene tu luz con Halus</p>
                </div>
                <div id="slider">
                    <figure>
                        <img class="sliderimg" src="/img/slide-1.webp" alt="paneles solares">
                        <img class="sliderimg" src="/img/slide-2.webp" alt="paneles solares">
                        <img class="sliderimg" src="/img/slide-3.webp" alt="paneles solares">
                        <img class="sliderimg" src="/img/slide-4.webp" alt="paneles solares">
                        <img class="sliderimg" src="/img/slide-1.webp" alt="paneles solares">
                    </figure>
                </div> 
            </section>

            <section id="nosotros" class="mb-5">
                <h2 class="outfit h2-o">¿Quiénes somos?</h2>
                <div class="container">
                    <img src="/img/logo-b.webp" class="logo-b">
                    <div class="about-p">
                        <div class="s-parrafo">
                            <h4 class="outfit">Energía accesible para todos</h4>
                            <p class="montserrat g-p">Conectamos hogares y empresas con soluciones solares asequibles y eficientes.</p>
                        </div>
                        
                        <div class="s-parrafo">
                            <h4 class="outfit">Un puente entre clientes y proveedores</h4>
                            <p class="montserrat g-p">Facilitamos la comunicación entre tú y la empresa que más te gusta, ofreciendo alternativas que tú necesitas.</p>
                        </div>
                        
                        <div class="s-parrafo">
                            <h4 class="outfit">Impulsando un futuro sustentable</h4>
                            <p class="montserrat g-p">Reducimos barreras para la adopción de energías limpias y fomentamos la eficiencia energética.</p>
                        </div>

                        <div class="s-parrafo">
                            <h4 class="outfit">Contáctanos</h4>
                            <p class="montserrat mb-1 g-p"> <i class="fa-solid fa-phone"></i> + 56 9 0000 9999</p> <a href="mailto:halus@halus.com" class="btn montserrat" target="_blank"> <i class="fa-solid fa-envelope"></i> Escríbenos</a>
                        </div>
                    </div>
                </div>

            </section>
            <section class="cont-sect mb-5" id="soluciones">
                <h2 class="outfit h2-o">¿Cuáles son nuestras soluciones?</h2>
                <p class="montserrat g-p">Te ofrecemos un lugar donde puedes encontrar los productos que necesitas y los proveedores que necesitas. ¿Alguna vez te has visto afectado por cortes del suministro eléctrico? Dejar de depender de una red eléctrica y sus complicaciones es algo que ahora está a tu alcance, con nuestro marketplace organizado y personalizado puedes comparar productos, precios y solicitar desde diferentes empresas justo lo que tú necesitas.</p>
                <p class="montserrat g-p">Experiencia el cambio, elige una fuente de energía limpia, renovable y confiable.</p>
            </section>
            <section class="cont-sect mb-5" id="comoFunciona">
                <h2 class="outfit h2-o">¿Cómo funciona?</h2>
                <p class="montserrat g-p">Halus se encarga de acercar empresas que se ajustan a tus necesidades. La posibilidad de comparar productos que se ajustan a tu presupuesto, con diversidad de proveedores para escoger el que más te gusta. Desde probar la experiencia con un arriendo semestral, hasta cambiarte definitivamente a esta mejor opción. Encontrarás en nosotros un mercado de energía ideal para adentrarte en este nuevo mundo.</p>
            </section>
            <section class="cont-sect mb-5" id="principios">
                <h2 class="outfit  h2-o">Nuestros principios</h2>
                <div class="container">
                    <div class="card-l">
                        <i class="fa-solid fa-handshake fa-card"></i>
                        <h5 class="outfit">Confianza y respaldo</h5>
                        <p class="montserrat">Garantía, soporte técnico y asesoría experta siempre disponibles.</p>
                    </div>
                    <div class="card-l">
                        <i class="fa-solid fa-hand-holding-dollar fa-card"></i>
                        <h5 class="outfit">Ahorro inteligente</h5>
                        <p class="montserrat">Optimiza tu consumo y disminuye costos con energía renovable.</p>
                    </div>
                    <div class="card-l">
                    	<i class="fa-solid fa-seedling fa-card"></i>
                        <h5 class="outfit">Sostenibilidad</h5>
                        <p class="montserrat">Comprometidos con soluciones energéticas responsables y renovables.</p>
                    </div>
                    <div class="card-l">
                    	<i class="fa-solid fa-solar-panel fa-card"></i>
                        <h5 class="outfit">Innovación</h5>
                        <p class="montserrat">Tecnología avanzada para maximizar eficiencia y rendimiento.</p>
                    </div>
                </div>
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
        <script>
            document.addEventListener('scroll', () =>{
                const toTopButton = document.querySelector('#to-top');
                if (window.scrollY > 800){
                    toTopButton.classList.add('show');
                } else {
                    toTopButton.classList.remove('show');
                }
            })
        </script>
    </body>
</html>