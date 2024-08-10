package app.waste2wealth.com.reportwaste

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.waste2wealth.com.tags.Groups
import app.waste2wealth.com.tags.Tag
import app.waste2wealth.com.tags.wasteGroups
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class ReportWasteViewModel @Inject constructor() : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    val tagsList = mutableStateListOf<Tag>()
    val selectedTags = mutableStateListOf<Tag>()

    val showTips = mutableStateOf(false)

    val tagsSearch = listOf(
        "Tags",
        "Plastic",
        "Glass",
        "Metal",
        "Paper",
        "Cardboard",
        "E-Waste",
        "Organic",
        "Textile",
        "Wood",
        "Rubber",
        "Other",
        "Tags",
    ).asSequence()
        .asFlow()
        .onEach { delay(3000) }

    private val _tags = MutableStateFlow(wasteGroups)
    val tags = searchText
        .debounce(100)
        .onEach { _isSearching.update { true } }
        .combine(_tags) { text, tags ->
            if (text.isBlank()) {
                tags
            } else {
                tags.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _tags.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}


