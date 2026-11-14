# Bodega MiPalomita

**Equipo:** CopyPasteDX
**Curso:** Desarrollo de Software Orientado a Objetos, EPIS - UNSA 2026

Bodega MiPalomita es un sistema de gestión para bodegas que permitirá registrar productos y ventas mediante un carrito de compras, calculando el total y el vuelto, descontando el stock en cada venta, generando la boleta y alertando cuando un producto esté por agotarse o vencer.

## Estado actual

**Sprint 1 (9-13 nov 2026):** clases base del modelo funcionando por consola: productos perecibles y no perecibles, inventario, métodos de pago y alertas.
**Siguiente:** interfaz gráfica (ventana principal, productos y carrito).

## Equipo

| Integrante | Rol |
|---|---|
| Andrade Arapa Luis Eduardo | Líder / Desarrollador |
| Ortiz Saavedra Sarah Megan | Desarrolladora |
| Quispe Sinticala María Isabel | Desarrolladora |

## Entregables

| Entregable | URL |
|---|---|
| Repositorio | https://github.com/mquispesin-456/DSOO---BodegaMiPalomita.git |

## Tecnología utilizada

| Ítem | Tecnología |
|---|---|
| Lenguaje | Java (JDK 17 o superior) |
| Control de versiones | Git y GitHub |
| Editor | Visual Studio Code |

## Cómo ejecutar

**Requisito:** tener instalado un JDK (17 o superior). Se verifica con `javac -version`.

1. Clonar el repositorio:

```bash
git clone https://github.com/mquispesin-456/DSOO---BodegaMiPalomita.git
cd DSOO---BodegaMiPalomita
```

2. Compilar (desde la carpeta `src`):

```bash
cd src
javac mipalomita/*/*.java mipalomita/*.java
```

3. Ejecutar el menú de gestión de productos:

```bash
java mipalomita.MenuProductos
java mipalomita.PruebaPagos
java mipalomita.PruebaProductos
```

## Estructura del proyecto

```text
DSOO---BodegaMiPalomita/
├── src/
│   └── mipalomita/
│       ├── MenuProductos.java        # Menú de consola para productos
│       ├── PruebaProductos.java      # Prueba de productos e inventario
│       ├── modelo/
│       │   ├── Producto.java         # Clase abstracta
│       │   ├── ProductoPerecible.java
│       │   ├── ProductoNoPerecible.java
│       │   └── (clases de pago y de venta: confirmar ubicación)
│       ├── alertas/                  # (confirmar)
│       │   ├── Alertable.java
│       │   ├── AlertaStock.java
│       │   └── AlertaVencimiento.java
│       └── gestion/
│           └── Inventario.java
├── docs/
│   └── diagrama-uml.png
└── README.md
```

## Referencias

- https://git-scm.com/
- https://github.com/
