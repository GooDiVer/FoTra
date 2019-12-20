package e.mi.fotra.koin

import e.mi.fotra.viewmodel.ChooseLanguageViewModel
import e.mi.fotra.viewmodel.ForumViewModel
import e.mi.fotra.viewmodel.TranslateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {

    val module = module {
        viewModel<TranslateViewModel> { TranslateViewModel(translateService = get(), languageGateway = get()) }
        viewModel<ChooseLanguageViewModel> { ChooseLanguageViewModel(languageGateway = get()) }
        viewModel<ForumViewModel> { ForumViewModel(forumGateway = get(), forumService = get())}
    }
}