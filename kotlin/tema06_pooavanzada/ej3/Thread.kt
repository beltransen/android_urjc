package tema06_pooavanzada.ej3

class Thread(val runnable: Runnable) {
    fun start() = runnable.run()
}