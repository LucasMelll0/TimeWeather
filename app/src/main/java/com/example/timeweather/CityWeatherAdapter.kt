package com.example.timeweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timeweather.model.City
import java.text.DecimalFormat

class CityWeatherAdapter(private val cityList : ArrayList<City>) :
    RecyclerView.Adapter<CityWeatherAdapter.CityWeatherViewHolder>() {
        class CityWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

            private val textViewNome : AppCompatTextView = itemView.findViewById(R.id.textview_nome_cidade)
            private val textViewUltimaAtualizacao: AppCompatTextView = itemView.findViewById(R.id.textview_ultima_atualizacao)
            private val textViewClima: AppCompatTextView = itemView.findViewById(R.id.textview_clima_cidade)
            private val textviewTemperatura: AppCompatTextView = itemView.findViewById(R.id.textview_temperatura_cidade)
            private val textviewTemperaturaMinima: AppCompatTextView = itemView.findViewById(R.id.textview_temperatura_minima_cidade)
            private val textviewTemperaturaMaxima: AppCompatTextView = itemView.findViewById(R.id.textview_temperatura_maxima_cidade)

            fun populaAdapter(city : City){
                textViewNome.text = city.nome
                textViewUltimaAtualizacao.text = "Atualizado as ${city.hora}"
                textViewClima.text = city.clima
                val decimalFormat = DecimalFormat("##.#")
                textviewTemperatura.text = "${decimalFormat.format(city.temperatura)}Cº"
                textviewTemperaturaMinima.text = "Min${decimalFormat.format(city.temp_min)}"
                textviewTemperaturaMaxima.text = "Max${decimalFormat.format(city.temp_max)}"
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityWeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_adapter_city, parent, false)

        return CityWeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityWeatherViewHolder, position: Int) {
        holder.populaAdapter(cityList[position])
    }

    override fun getItemCount(): Int {
        return cityList.size
    }
}