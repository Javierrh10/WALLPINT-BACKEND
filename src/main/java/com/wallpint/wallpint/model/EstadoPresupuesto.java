package com.wallpint.wallpint.model;

/**
 * Esta clase ...
 *
 * @author : Javier Raposo Huelva
 * @version : 2026:04
 */
public enum EstadoPresupuesto {
    PENDIENTE, // El presupuesto ha sido creado pero aún no ha sido revisado por el pintor.
    ACEPTADO,  // El pintor ha aceptado el presupuesto y se ha programado la fecha de inicio del trabajo.
    RECHAZADO, // El pintor ha rechazado el presupuesto, lo que significa que no realizará el trabajo.
    FINALIZADO  // El trabajo ha sido completado y el presupuesto se ha cerrado.
}
