package scb.academy.jinglebell.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import scb.academy.jinglebell.R
import scb.academy.jinglebell.extension.setImageUrl
import scb.academy.jinglebell.model.Country

class CountryAdapter(
        countries: List<Country> = listOf()
) : ListAdapter<Country, CountryItemViewHolder>(CountryItemCallback()) {

    init {
        submitList(countries)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryItemViewHolder(parent)

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CountryItemCallback : DiffUtil.ItemCallback<Country>() {

    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean =
            oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean =
            oldItem == newItem

}

class CountryItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
) {

    private val tvName: TextView = itemView.findViewById(R.id.tv_country_name)
    private val tvCapital: TextView = itemView.findViewById(R.id.tv_country_capital)
    private val ivFlag: ImageView = itemView.findViewById(R.id.iv_country_flag)

    fun bind(country: Country) {
        tvName.text = country.name
        tvCapital.text = "Capital: ${country.capital}"
        ivFlag.setImageUrl(country.imageUrl)
    }

}
