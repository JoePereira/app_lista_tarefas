//Joe
package com.example.applistadetarefas

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.text.InputType
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private lateinit var tarefas:ArrayList<String>
    private lateinit var adapter:ArrayAdapter<String>


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tarefas = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tarefas)

        val ltsTarefas = findViewById<ListView>(R.id.ltsTarefas)
        val fabTarefa = findViewById<FloatingActionButton>(R.id.fabTarefa)

        ltsTarefas.adapter = adapter

        fabTarefa.setOnClickListener(View.OnClickListener {
            adicionarTarefa()
        })

    }
    private fun adicionarTarefa() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Adicionar Tarefa")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton("Adicionar"){
            _, _ ->
            val novaTarefa = input.text.toString().trim()
            if(novaTarefa.isNotEmpty()){
                tarefas.add(novaTarefa)
                adapter.notifyDataSetChanged()
            }
        }
        builder.setNegativeButton("Cancelar"){dialog, _ -> dialog.cancel()}

        val dialog = builder.create()
        dialog.show()
    }
}