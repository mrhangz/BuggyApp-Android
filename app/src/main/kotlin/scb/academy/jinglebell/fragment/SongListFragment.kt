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
import scb.academy.jinglebell.adapter.OnSongClickListener
import scb.academy.jinglebell.adapter.SongAdapter
import scb.academy.jinglebell.extension.showToast
import scb.academy.jinglebell.model.Song
import scb.academy.jinglebell.model.SongSearchResult
import scb.academy.jinglebell.service.ApiManager

class SongListFragment : Fragment(), OnSongClickListener {

    private lateinit var rvSongs: RecyclerView
    private lateinit var songAdapter: SongAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_song_list, container, false)
    }

    private val songListCallback = object : Callback<SongSearchResult> {
        override fun onFailure(call: Call<SongSearchResult>, t: Throwable) {
            context?.showToast("Can not call country list $t")
        }

        override fun onResponse(call: Call<SongSearchResult>, response: Response<SongSearchResult>) {
            context?.showToast("Success")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSongs = view.findViewById(R.id.rv_rooms)
        songAdapter = SongAdapter(this)
        rvSongs.adapter = songAdapter
        rvSongs.layoutManager = LinearLayoutManager(context)
        rvSongs.itemAnimator = DefaultItemAnimator()
        rvSongs.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        loadSongs()
    }

    private fun loadSongs()  {
        ApiManager.artistService.songs().enqueue(songListCallback)
    }

    override fun onSongClick(song: Song) {

    }
}
