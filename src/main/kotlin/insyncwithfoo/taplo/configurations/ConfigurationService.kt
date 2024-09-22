package insyncwithfoo.taplo.configurations

import com.intellij.openapi.components.RoamingType
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.SimplePersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service
import insyncwithfoo.taplo.findExecutableInPath
import insyncwithfoo.taplo.toPathIfItExists
import java.nio.file.Path


@State(name = "insyncwithfoo.taplo", storages = [Storage("taplo.xml", roamingType = RoamingType.LOCAL)])
@Service(Service.Level.APP)
internal class TaploConfigurationService : SimplePersistentStateComponent<TaploConfigurations>(TaploConfigurations()) {
    
    companion object {
        fun getInstance() = service<TaploConfigurationService>()
    }
    
}


internal val taploConfigurations: TaploConfigurations
    get() = TaploConfigurationService.getInstance().state.copy()


internal val taploExecutable: Path?
    get() = taploConfigurations.executable?.toPathIfItExists() ?: findExecutableInPath("taplo")
