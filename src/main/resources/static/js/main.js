let chart = null;
function calcularAhorro() {
    let consumoMensual = parseFloat(document.getElementById("consumo").value);
    let tarifaElectrica = parseFloat(document.getElementById("costo").value);
    let superficieDisponible = parseFloat(document.getElementById("superficie").value);
    let inversionInicial = parseFloat(document.getElementById("inversion").value);
    
    // Cálculo de la producción de energía solar
    let horasSolDiarias = 9;
    let numPaneles = superficieDisponible / 1.6; // Paneles estándar de 1.6 m²
    let potenciaTotal = numPaneles * 400; // Cada panel genera 400W
    let produccionSolarMensual = (potenciaTotal * horasSolDiarias * 30) / 1000; // Conversión a kWh
    let produccionSolarAnual = produccionSolarMensual * 12;
    
    // Cálculo de costos y ahorros
    let consumoAnual = consumoMensual * 12;
    let costoAnualActual = consumoAnual * tarifaElectrica;
    let consumoRestante = Math.max(0, consumoAnual - produccionSolarAnual);
    let costoAnualNuevo = consumoRestante * tarifaElectrica;
    let ahorroAnual = costoAnualActual - costoAnualNuevo;
    
    let anios = 5;
    let ahorroTotal = [];
    
    for (let i = 0; i <= anios; i++) {
        ahorroTotal.push(i * ahorroAnual - inversionInicial);
    }
    
    let ctx = document.getElementById("graficoAhorro").getContext("2d");

    // Destruir el gráfico anterior si ya existe
    if (chart) {
        chart.destroy();
    }

    chart = new Chart(ctx, {
        type: "line",
        data: {
            labels: [...Array(anios + 1).keys()],
            datasets: [{
                label: "Ahorro Acumulado ($)",
                data: ahorroTotal,
                backgroundColor: "darkcyan",
                borderColor: "black",
                fill: false,
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false, 
            scales: {
                x: { title: { display: true, text: "Años" } },
                y: { title: { display: true, text: "Ahorro ($)" } }
            }
        }
    });
}
