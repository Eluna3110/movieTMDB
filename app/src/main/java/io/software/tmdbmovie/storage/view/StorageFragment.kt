package io.software.tmdbmovie.storage.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import io.software.functionalutilities.Constants
import io.software.tmdbmovie.R
import io.software.tmdbmovie.databinding.StorageFragmentBinding
import java.util.HashMap

class StorageFragment : Fragment()
{
    private lateinit var binding: StorageFragmentBinding
    private val database = Firebase.database
    private val myRef = database.getReference(Constants.DB_REFERENCE)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StorageFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.uploadImageView.setOnClickListener {
            fileUpload()
        }
    }

    private fun fileUpload() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        getResult.launch(intent)
    }

    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            if (it.resultCode == Activity.RESULT_OK) {
                val fileUri = it.data!!.data
                val folder: StorageReference =
                    FirebaseStorage.getInstance().reference.child(Constants.NAME_BD)

                val fileName: StorageReference = folder.child("file" + fileUri!!.lastPathSegment)
                fileName.putFile(fileUri).addOnSuccessListener {
                    fileName.downloadUrl.addOnSuccessListener { uri ->
                        val hashMap = HashMap<String, String>()
                        hashMap["link"] = java.lang.String.valueOf(uri)
                        myRef.setValue(hashMap)
                    }
                }
                Toast.makeText(this.context,getString(R.string.storage_toast),Toast.LENGTH_LONG).show()
            }
        }
}