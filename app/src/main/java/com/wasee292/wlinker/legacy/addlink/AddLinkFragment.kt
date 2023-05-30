package com.wasee292.wlinker.legacy.addlink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wasee292.wlinker.legacy.addtag.AddTagFragment
import com.wasee292.wlinker.legacy.databinding.BottomSheetDialogFragmentAddLinkBinding
import com.wasee292.wlinker.legacy.db.entity.Link
import com.wasee292.wlinker.legacy.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddLinkFragment : BottomSheetDialogFragment() {
    private var _binding: BottomSheetDialogFragmentAddLinkBinding? = null
    private val binding get() = _binding!!
    private val addLinkViewModel: AddLinkViewModel by viewModels()
    private val selectableTagsAdapter by lazy { SelectableTagsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetDialogFragmentAddLinkBinding.inflate(inflater, container, false)
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
            btnCreateTag.setOnClickListener {
                openAddTagDialog()
            }
            btnAddLink.setOnClickListener {
                val link = etLink.text.toString()
                if (link.isNotEmpty() && selectableTagsAdapter.currentList.any { it.isSelected }) {
                    addLinkViewModel.addLink(
                        Link(
                            link,
                            selectableTagsAdapter.currentList.filter { it.isSelected }
                                .map { it.data }
                        )
                    )
                } else {
					shortToast {
						if (link.isEmpty()) {
							"please enter link"
						} else {
							"please select tags"
						}
					}
				}
            }
            rvSelectTag.layoutManager = LinearLayoutManager(requireContext())
            rvSelectTag.adapter = selectableTagsAdapter
        }
    }

    private fun observeEvents() {
        addLinkViewModel.events.observe(this) { addLinkEvent ->
            when (addLinkEvent) {
                is AddLinkEvent.TagsUpdated -> {
                    selectableTagsAdapter.submitList(addLinkEvent.tags)
                }

                is AddLinkEvent.LinkAdded -> {
                    dismiss()
                }

                else -> {
                    // do nothing
                }
            }
        }
    }

    private fun openAddTagDialog() {
        AddTagFragment.show(requireActivity().supportFragmentManager)
    }

    companion object {
        private const val TAG = "add_link_fragment"
        fun show(fragmentManager: FragmentManager) = AddLinkFragment().show(fragmentManager, TAG)
    }
}
