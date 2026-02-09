package urjc.lsmu.listatareas.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TareasViewModel: ViewModel() {
    private val _tareas = mutableListOf<Tarea>() // Guarda los datos
    private val _listaTareas = MutableLiveData<List<Tarea>>() // Permite observar los datos
    val listaTareas: LiveData<List<Tarea>> // Expone el observable sin permitir editarlo
        get() = _listaTareas

    fun addTarea(tarea: Tarea){
        _tareas.add(tarea) // Añade el nuevo elemento
        _listaTareas.value = _tareas // Actualiza el observable para notificar a los observadores
    }

    fun editTarea(tarea: Tarea){
        for (t in _tareas){
            if (t.id == tarea.id){
                t.titulo = tarea.titulo
                t.descripcion = tarea.descripcion
                t.completada = tarea.completada
                break
            }
        }
        _listaTareas.value = _tareas // Actualiza el observable para notificar a los observadores
    }

    fun delTarea(index: Int){
        _tareas.removeAt(index)
        _listaTareas.value = _tareas
    }
}