
// Variable global para almacenar los datos JSON

function recibirDatosDesdeControlador() {
    fetch('/sectores')
        .then(response => response.json())
        .then(data => {
            for (const inmueble of data) {
                console.log("Sector: " + inmueble.sector);
                console.log("Cantidad: " + inmueble.cantidad);
            }

            // Aquí puedes realizar otras operaciones con los datos recibidos desde el controlador
        })
        .catch(error => {
            console.error('Error al obtener los datos:', error);
        });
}
    var datosInmuebles = [];/* Aquí asigna el valor de los datos recibidos del controlador */;
/*
document.addEventListener('DOMContentLoaded', function () {
    fetch('/sectores')
        .then(response => response.json())
        .then(data => {
            // Asignar los datos recibidos a la variable datosInmuebles
            datosInmuebles = data;
            
            Highcharts.chart('container3', {
			    chart: {
			        type: 'column'
			    },
			    title: {
			        align: 'left',
			        text: 'Inmuebles por sector'
			    },
			    subtitle: {
			        align: 'left',
			        text: 'Todos los inmuebles'
			    },
			    accessibility: {
			        announceNewData: {
			            enabled: true
			        }
			    },
			    xAxis: {
				    categories: datosInmuebles.map(inmueble => inmueble.sector),
				    type: 'category'
				},
			    yAxis: {
			        title: {
			            text: 'Cantidad'
			        }
			
			    },
			    legend: {
			        enabled: false
			    },
			    plotOptions: {
			        series: {
			            borderWidth: 0,
			            dataLabels: {
			                enabled: true,
			                format: '{point.y:.1f}%'
			            }
			        }
			    },
			
			    tooltip: {
			        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
			        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
			    },
			
			    series: [
			        {
			            name: 'Sectores',
			            colorByPoint: true,
			            data: datosInmuebles.map(inmueble => {
		                    return {
		                        name: inmueble.sector,
		                        y: inmueble.cantidad
		                    };
		                })            
			            
			        }
			    ],
			    drilldown: {
		    // Configuración del drilldown (puedes eliminar este bloque si no necesitas la funcionalidad de drilldown)
				}
			
			});
        })
        .catch(error => {
            console.error('Error al obtener los datos:', error);
        });
});*/


/*
document.addEventListener('DOMContentLoaded', function () {
	        const chart = // Data retrieved from https://fas.org/issues/nuclear-weapons/status-world-nuclear-forces/
	        	Highcharts.chart('basicArea', {
	        	    chart: {
	        	        type: 'area'
	        	    },
	        	    accessibility: {
	        	        description: 'Image description: An area chart compares the nuclear stockpiles of the USA and the USSR/Russia between 1945 and 2017. The number of nuclear weapons is plotted on the Y-axis and the years on the X-axis. The chart is interactive, and the year-on-year stockpile levels can be traced for each country. The US has a stockpile of 6 nuclear weapons at the dawn of the nuclear age in 1945. This number has gradually increased to 369 by 1950 when the USSR enters the arms race with 6 weapons. At this point, the US starts to rapidly build its stockpile culminating in 32,040 warheads by 1966 compared to the USSR’s 7,089. From this peak in 1966, the US stockpile gradually decreases as the USSR’s stockpile expands. By 1978 the USSR has closed the nuclear gap at 25,393. The USSR stockpile continues to grow until it reaches a peak of 45,000 in 1986 compared to the US arsenal of 24,401. From 1986, the nuclear stockpiles of both countries start to fall. By 2000, the numbers have fallen to 10,577 and 21,000 for the US and Russia, respectively. The decreases continue until 2017 at which point the US holds 4,018 weapons compared to Russia’s 4,500.'
	        	    },
	        	    title: {
	        	        text: 'US and USSR nuclear stockpiles'
	        	    },
	        	    subtitle: {
	        	        text: 'Source: <a href="https://fas.org/issues/nuclear-weapons/status-world-nuclear-forces/" ' +
	        	            'target="_blank">FAS</a>'
	        	    },
	        	    xAxis: {
	        	        allowDecimals: false,
	        	        accessibility: {
	        	            rangeDescription: 'Range: 1940 to 2017.'
	        	        }
	        	    },
	        	    yAxis: {
	        	        title: {
	        	            text: 'Nuclear weapon states'
	        	        }
	        	    },
	        	    tooltip: {
	        	        pointFormat: '{series.name} had stockpiled <b>{point.y:,.0f}</b><br/>warheads in {point.x}'
	        	    },
	        	    plotOptions: {
	        	        area: {
	        	            pointStart: 1940,
	        	            marker: {
	        	                enabled: false,
	        	                symbol: 'circle',
	        	                radius: 2,
	        	                states: {
	        	                    hover: {
	        	                        enabled: true
	        	                    }
	        	                }
	        	            }
	        	        }
	        	    },
	        	    series: [{
	        	        name: 'USA',
	        	        data: [
	        	            null, null, null, null, null, 2, 9, 13, 50, 170, 299, 438, 841,
	        	            1169, 1703, 2422, 3692, 5543, 7345, 12298, 18638, 22229, 25540,
	        	            28133, 29463, 31139, 31175, 31255, 29561, 27552, 26008, 25830,
	        	            26516, 27835, 28537, 27519, 25914, 25542, 24418, 24138, 24104,
	        	            23208, 22886, 23305, 23459, 23368, 23317, 23575, 23205, 22217,
	        	            21392, 19008, 13708, 11511, 10979, 10904, 11011, 10903, 10732,
	        	            10685, 10577, 10526, 10457, 10027, 8570, 8360, 7853, 5709, 5273,
	        	            5113, 5066, 4897, 4881, 4804, 4717, 4571, 4018, 3822, 3785, 3805,
	        	            3750, 3708, 3708
	        	        ]
	        	    }, {
	        	        name: 'USSR/Russia',
	        	        data: [null, null, null, null, null, null, null, null, null,
	        	            1, 5, 25, 50, 120, 150, 200, 426, 660, 863, 1048, 1627, 2492,
	        	            3346, 4259, 5242, 6144, 7091, 8400, 9490, 10671, 11736, 13279,
	        	            14600, 15878, 17286, 19235, 22165, 24281, 26169, 28258, 30665,
	        	            32146, 33486, 35130, 36825, 38582, 40159, 38107, 36538, 35078,
	        	            32980, 29154, 26734, 24403, 21339, 18179, 15942, 15442, 14368,
	        	            13188, 12188, 11152, 10114, 9076, 8038, 7000, 6643, 6286, 5929,
	        	            5527, 5215, 4858, 4750, 4650, 4600, 4500, 4490, 4300, 4350, 4330,
	        	            4310, 4495, 4477
	        	        ]
	        	    }]
	        	});
	    });
	    
	    */	    
//==============================================================================================================


// Data retrieved from https://gs.statcounter.com/browser-market-share#monthly-202201-202201-bar

// Create the chart

/*document.addEventListener('DOMContentLoaded', function () {
	Highcharts.chart('container3', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        align: 'left',
	        text: 'Browser market shares. January, 2022'
	    },
	    subtitle: {
	        align: 'left',
	        text: 'Click the columns to view versions. Source: <a href="http://statcounter.com" target="_blank">statcounter.com</a>'
	    },
	    accessibility: {
	        announceNewData: {
	            enabled: true
	        }
	    },
	    xAxis: {
		    categories: datosInmuebles.map(inmueble => inmueble.sector),
		    type: 'category'
		},
	    yAxis: {
	        title: {
	            text: 'Cantidad'
	        }
	
	    },
	    legend: {
	        enabled: false
	    },
	    plotOptions: {
	        series: {
	            borderWidth: 0,
	            dataLabels: {
	                enabled: true,
	                format: '{point.y:.1f}%'
	            }
	        }
	    },
	
	    tooltip: {
	        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
	        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
	    },
	
	    series: [
	        {
	            name: 'Sectores',
	            colorByPoint: true,
	            data: datosInmuebles.map(inmueble => {
			        return {
			            name: inmueble.sector,
			            y: inmueble.cantidad
			            };
		    		})	            
	            
	        }
	    ],
	    drilldown: {
    // Configuración del drilldown (puedes eliminar este bloque si no necesitas la funcionalidad de drilldown)
		}
	
	});
	
}); */   
