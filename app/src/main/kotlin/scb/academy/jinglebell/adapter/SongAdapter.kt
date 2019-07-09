package scb.academy.jinglebell.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import scb.academy.jinglebell.R
import scb.academy.jinglebell.extension.setImageUrl
import scb.academy.jinglebell.model.Song

class SongAdapter(private val listener: OnSongClickListener)
    : RecyclerView.Adapter<SongItemViewHolder>() {

    /**
     * List songs of artris
     */
    val songs: List<Song>
        get() = _songs

    private var _songs: List<Song> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SongItemViewHolder(parent)

    override fun onBindViewHolder(holder: SongItemViewHolder, position: Int) {
        holder.bind(_songs[position], listener)
    }

    override fun getItemCount(): Int {
        return if (songs.count() == 0) {
            0
        } else {
            songs.count() + 1
        }
    }

    fun submitList(list: List<Song>) {
        _songs = list
        notifyDataSetChanged()
    }

}

class SongItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
) {

    private val ivSongArtwork: ImageView = itemView.findViewById(R.id.iv_song_artwork)
    private val tvSongName: TextView = itemView.findViewById(R.id.tv_song_name)
    private val tvSongArtist: TextView = itemView.findViewById(R.id.tv_song_artist)
    private val tvSongPrice: TextView = itemView.findViewById(R.id.tv_song_price)

    fun bind(song: Song, listener: OnSongClickListener) {
        tvSongName.text = song.name
        tvSongArtist.text = song.artistName
        tvSongPrice.text = "${song.price} ${song.priceCurrency}"
        ivSongArtwork.setImageUrl(song.artworkUrl)

        itemView.setOnClickListener { listener.onSongClick(song) }
    }

}

interface OnSongClickListener {
    fun onSongClick(song: Song)
}
