package br.unipar.rodrigostravel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class AdapterLista (

    private val context: Context,
    private val listaViagens: MutableList<ViagensAdapter>)
    : ArrayAdapter<ViagensAdapter>(context,0, listaViagens) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val viagem = listaViagens.get(position)
        val view = LayoutInflater.from(context).inflate(R.layout.lista_viagens, parent, false)

        val destino = view.findViewById<TextView>(R.id.tvDestino)
        val data = view.findViewById<TextView>(R.id.tvData)
        val gasto = view.findViewById<TextView>(R.id.tvGasto)

        destino.setText(viagem.destino)
        data.setText(viagem.data)
        gasto.setText(viagem.gasto)

        return view
    }
}