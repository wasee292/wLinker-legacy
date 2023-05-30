package com.wasee292.wlinker.legacy.addtag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.wasee292.wlinker.legacy.databinding.DialogFragmentAddTagBinding
import com.wasee292.wlinker.legacy.db.entity.Tag
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTagFragment : DialogFragment() {
    private var _binding: DialogFragmentAddTagBinding? = null
    private val binding get() = _binding!!
    private val addTagViewModel: AddTagViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentAddTagBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeEvents()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupUI() {
        binding.apply {
            btnAddTag.setOnClickListener {
                val tag: String = etTag.text.toString()
                if (tag.isNotEmpty()) {
                    addTagViewModel.addTag(Tag(tag))
                } else {
					shortToast { "please enter tag" }
                }
            }
        }
    }

    private fun observeEvents() {
        addTagViewModel.events.observe(this@AddTagFragment) { addTagEvent ->
            when (addTagEvent) {
                is AddTagEvent.TagAdded -> {
                    dismiss()
                }

                else -> {
                    // do nothing
                }
            }
        }
    }

    companion object {
        private const val TAG = "add_tag_fragment"
        fun show(fragmentManager: FragmentManager) {
            AddTagFragment().show(fragmentManager, TAG)
        }
    }
}
