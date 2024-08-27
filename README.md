# Scheduling-Algorithm (Memory Simulator)
## Descripción
- Este programa simula como se comportaría un algoritmo de despacho, o calendarización de procesos, cuando se usan particiones de memoria fijas.
- ## Detalles
	* Se simulará un rango de 1024 kB
    * La memoria se dividirá en cuatro secciones de distintos tamaños
    * Se gestionará el alojamiento de memoria meidante 4 colas diferentes, una para cada sección de la memoria
    * El algoritmo de despacho se basa en el modelo FIFO (First In -First Out)

## Features
- Version 1.0: El generatorTask filtrara los procesos y los enlistara en una lista ligada correspondiente al tamaño de cada particion o menor, los procesos en Sched seran procesados de manera FIFO.

- Version 2.0: Habra una mejora en el Sched, si alguna particion se encuentra en Free por mas de dos segundos se le enlistara algun proceso de alguna otra particion que le quepa. 