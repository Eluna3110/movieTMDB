package io.software.tmdbmovie.perfil.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import io.software.tmdbmovie.databinding.PerfilFragmentBinding
import io.software.tmdbmovie.movie.MovieError
import io.software.tmdbmovie.perfil.viewmodel.ProfileViewModel

class ProfileFragment : Fragment()
{
    private lateinit var binding: PerfilFragmentBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PerfilFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        binding.viewModel = viewModel

        this.context?.let { viewModel.initAdapter(it) }

        viewModel.uri.observe(viewLifecycleOwner, Observer { fileUri ->
            Picasso.with(this.context)
                .load(fileUri)
                .resize(150, 165)
                .into(binding.imageView)
        })

        binding.labelEditPerfil.setOnClickListener {
            fileUpload()
        }
    }

    private fun fileUpload() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*")
        getResult.launch(intent)
    }

    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            if (it.resultCode == Activity.RESULT_OK) {
                val fileUri = it.data!!.data

                viewModel.setUri(fileUri!!)
            }
        }

    private fun initViewModel() {
        viewModel = activity?.run { ViewModelProvider(this)[ProfileViewModel::class.java] } ?: throw  Exception("Invalid Activity")
    }
}