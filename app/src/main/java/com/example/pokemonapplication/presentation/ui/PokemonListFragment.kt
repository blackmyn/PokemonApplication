import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapplication.R
import com.example.pokemonapplication.data.model.Pokemon
import com.example.pokemonapplication.presentation.model.PokemonListViewModel
import com.example.pokemonapplication.presentation.ui.PokemonListAdapter

class PokemonListFragment : Fragment() {

    private lateinit var viewModel: PokemonListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PokemonListAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewPokemonList)
        progressBar = view.findViewById(R.id.progressBar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupRecyclerView() {
        adapter = PokemonListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(PokemonListViewModel::class.java)
        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is PokemonListViewModel.ViewState.Loading -> showLoadingState()
                is PokemonListViewModel.ViewState.Success -> {
                    hideLoadingState()
                    showPokemonList(state.pokemonList)
                }

                is PokemonListViewModel.ViewState.Error -> {
                    hideLoadingState()
                    showErrorState(state.message)
                }
            }
        })
    }

    private fun showLoadingState() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun hideLoadingState() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    private fun showPokemonList(pokemonList: List<Pokemon>) {
        adapter.submitList(pokemonList)
    }

    private fun showErrorState(message: String) {
        // Здесь можно использовать Toast или AlertDialog для отображения сообщения об ошибке
    }
}
