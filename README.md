# Equipo: CopyPasteDX 
# Proyecto: BodegaMiPalomita
Bodega MiPalomita es un sistema de gestión para bodegas que permite registrar productos y ventas de forma rápida mediante un carrito de compras, calculando automáticamente el total y el vuelto. La aplicación descuenta el stock en cada venta, genera la boleta correspondiente y alerta cuando un producto está por agotarse o vencer, además de ofrecer un resumen de las ventas del día.

| Equipo | Rol | Porcentaje |
| :--- | :--- | :---: |
| Andrade Arapa Luis Eduardo | Desarrolladores | 100% |
| Ortiz Saavedra Sarah Megan | Desarrolladores | 100% |
| Quispe Sinticala Maria Isabel  | Desarrolladores | 100% |
| | **Total** | **100%** |

| Entregables | URL |
| :--- | :--- |
| Repositorio | https://github.com/mquispesin-456/DSOO---BodegaMiPalomita.git |
| **Informes**[^1] | |


# Tecnología utilizada[^2]

| Ítem | Tecnología |
| :--- | :--- |
| **Desarrollo** | GNU/Linux |
| | Git |
| | Github |


# Estructura del proyecto
```bash
MiPalomita/
├── src/
│   └── mipalomita/
│       ├── Main.java                    # Menú principal
│       ├── modelo/                      # Productos y ventas
│       │   ├── Producto.java            # Clase abstracta
│       │   ├── ProductoPerecible.java
│       │   ├── ProductoNoPerecible.java
│       │   ├── ItemVenta.java
│       │   ├── Carrito.java
│       │   ├── Boleta.java
│       │   └── Venta.java
│       ├── alertas/                     # Interfaz y sus implementaciones
│       │   ├── Alertable.java           # Interfaz
│       │   ├── AlertaStock.java
│       │   └── AlertaVencimiento.java
│       └── gestion/                     # Clases que administran todo
│           ├── Inventario.java
│           ├── Bodega.java
│           └── ReporteDiario.java
├── docs/
│   └── diagrama-uml.png                 
├── .gitignore
└── README.md
```

# Referencias
- https://askubuntu.com/
- https://git-scm.com/
- https://github.com/
