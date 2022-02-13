package io.corp.calculator.trace;

/**
 * API para el traceo de operaciones.
 */
public interface TracerAPI {

    /**
     * Delega en un sistema de trazas el resultado obtenido.
     *
     * @param result resultado a tracear.
     * @param <T> tipo del resultado traceado.
     */
    public <T> void trace( final T result );

}
