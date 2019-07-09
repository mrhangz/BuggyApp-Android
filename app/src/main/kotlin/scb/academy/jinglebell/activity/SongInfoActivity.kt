package scb.academy.jinglebell.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import scb.academy.jinglebell.R
import scb.academy.jinglebell.extension.setImageUrl
import scb.academy.jinglebell.model.Song
import java.text.SimpleDateFormat
import java.util.*

class SongInfoActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_SONG = "song"

        private const val DATE_FORMAT_ISO_8601 = "YYYY-MM-dd'T'HH:mm:ss'Z'"
        private const val DATE_FORMAT_DATE_ONLY = "YYYY-MM-dd"

        fun startActivity(context: Context, song: Song? = null) =
            context.startActivity(Intent(context, SongInfoActivity::class.java))
    }

    private lateinit var ivSongArtWork: ImageView
    private lateinit var tvSongName: TextView
    private lateinit var tvSongAlbum: TextView
    private lateinit var tvSongReleaseDate: TextView
    private lateinit var tvSongGenre: TextView
    private lateinit var tvSongTrackPrice: TextView
    private lateinit var tvSongCollectionPrice: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_info)

        ivSongArtWork = findViewById(R.id.iv_song_artwork)
        tvSongName = findViewById(R.id.tv_song_name)
        tvSongAlbum = findViewById(R.id.tv_song_album)
        tvSongReleaseDate = findViewById(R.id.tv_release_date)
        tvSongGenre = findViewById(R.id.tv_genre)
        tvSongTrackPrice = findViewById(R.id.tv_track_price)
        tvSongCollectionPrice = findViewById(R.id.tv_collection_price)

        val song = intent.getParcelableExtra<Song>(EXTRA_SONG) ?: return
        showSongInformation(song)
    }

    private fun showSongInformation(song: Song) {
        title = song.artistName

        ivSongArtWork.setImageUrl(song.artworkUrl)
        tvSongName.text = song.name
        tvSongAlbum.text = song.album

        tvSongGenre.text = song.genre
        tvSongTrackPrice.text = "${song.price} ${song.priceCurrency}"
        tvSongCollectionPrice.text = "${song.collectionPrice} ${song.priceCurrency}"
        val releaseDate = SimpleDateFormat(DATE_FORMAT_ISO_8601).parse(song.releaseDate)
        tvSongReleaseDate.text = releaseDate?.let { date ->
            SimpleDateFormat(DATE_FORMAT_DATE_ONLY, Locale.getDefault())
                .format(date)
                .toString()
        } ?: "-"
    }
}
