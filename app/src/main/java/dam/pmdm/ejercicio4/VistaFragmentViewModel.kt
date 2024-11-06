package dam.pmdm.ejercicio4

import androidx.lifecycle.ViewModel

class VistaFragmentViewModel:ViewModel() {
    var estadoActual:Estado = Estado.NO
    lateinit var figuraGeometrica:FiguraGeometrica

    fun seleccionarEstado(estado: Estado){
        estadoActual = estado
    }
}