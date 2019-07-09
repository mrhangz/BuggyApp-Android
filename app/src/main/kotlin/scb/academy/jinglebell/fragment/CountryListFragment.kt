package scb.academy.jinglebell.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import scb.academy.jinglebell.R
import scb.academy.jinglebell.adapter.CountryAdapter
import scb.academy.jinglebell.extension.showToast
import scb.academy.jinglebell.model.Country
import scb.academy.jinglebell.service.ApiManager

class CountryListFragment : Fragment() {

    private lateinit var rvCountryList: RecyclerView
    private lateinit var countryAdapter: CountryAdapter

    private val countryListCallback = object : Callback<List<Country>> {
        override fun onFailure(call: Call<List<Country>>, t: Throwable) {
            context?.showToast("Can not call country list $t")
        }

        override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
            val countries = response.body() ?: return
            countryAdapter.submitList(countries)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_country_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCountryList = view.findViewById(R.id.rv_country_list)
        rvCountryList.apply {
            adapter = CountryAdapter()
                .also { countryAdapter = it }
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        loadCountries()
    }

    private fun loadCountries() {
        ApiManager.countryService.countries().enqueue(countryListCallback)
    }
}
