package dam.pmdm.ejercicio4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dam.pmdm.ejercicio4.databinding.FragmentVistaBinding

class VistaFragment : Fragment() {

    private var _binding:FragmentVistaBinding? = null
    private val binding:FragmentVistaBinding
        get() = checkNotNull(_binding)

    private lateinit var viewModel: VistaFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater, container)
        inicializarViewModel()
        inicializarMaterialToolbar()
        ocultarComponentes()
        inicializarBtnFigura()
        inicializarBotonesCalcArea()
        inicializarBotonesCalcVol()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        ocultarComponentes()
        actualizarSpinner()
        habilitarComponentes()
        habilitarImagen()
    }

    private fun inicializarBinding(inflater:LayoutInflater, container:ViewGroup?){
        _binding = FragmentVistaBinding.inflate(inflater, container, false)
    }

    private fun inicializarViewModel(){
        viewModel = ViewModelProvider(this).get(VistaFragmentViewModel::class.java)
    }

    private fun inicializarMaterialToolbar() {
        val mainActivity:MainActivity = activity as MainActivity
        mainActivity.setSupportActionBar(binding.toolbar)
    }

    private fun ocultarComponentes(){
        binding.txtLado.visibility = View.GONE
        binding.txtAreaBase.visibility = View.GONE
        binding.txtPerBase.visibility = View.GONE
        binding.txtAltura.visibility = View.GONE
        binding.txtApotema.visibility = View.GONE
        binding.txtRadio.visibility = View.GONE
        binding.txtGener.visibility = View.GONE
        binding.btnCalcArea.visibility = View.GONE
        binding.btnCalcVol.visibility = View.GONE
    }

    private fun inicializarBtnFigura(){
        binding.btnSelec.setOnClickListener {
            ocultarComponentes()
            limpiarCampos()
            when (binding.spnFiguras.selectedItem.toString()) {
                "Cubo" -> viewModel.seleccionarEstado(Estado.CUBO)
                "Prisma" -> viewModel.seleccionarEstado(Estado.PRISMA)
                "Pirámide" -> viewModel.seleccionarEstado(Estado.PIRAMIDE)
                "Cilindro" -> viewModel.seleccionarEstado(Estado.CILINDRO)
                "Cono" -> viewModel.seleccionarEstado(Estado.CONO)
                "Esfera" -> viewModel.seleccionarEstado(Estado.ESFERA)
            }
            habilitarComponentes()
            habilitarImagen()
        }
    }

    private fun habilitarComponentes() {
        when (viewModel.estadoActual) {
            Estado.CUBO -> {
                binding.txtLado.visibility = View.VISIBLE
                habilitarBotonesCalc()
            }
            Estado.PRISMA -> {
                binding.txtAreaBase.visibility = View.VISIBLE
                binding.txtPerBase.visibility = View.VISIBLE
                binding.txtAltura.visibility = View.VISIBLE
                habilitarBotonesCalc()
            }
            Estado.PIRAMIDE -> {
                binding.txtAreaBase.visibility = View.VISIBLE
                binding.txtPerBase.visibility = View.VISIBLE
                binding.txtApotema.visibility = View.VISIBLE
                binding.txtAltura.visibility = View.VISIBLE
                habilitarBotonesCalc()
            }
            Estado.CILINDRO -> {
                binding.txtRadio.visibility = View.VISIBLE
                binding.txtAltura.visibility = View.VISIBLE
                habilitarBotonesCalc()
            }
            Estado.CONO -> {
                binding.txtRadio.visibility = View.VISIBLE
                binding.txtGener.visibility = View.VISIBLE
                binding.txtAltura.visibility = View.VISIBLE
                habilitarBotonesCalc()
            }
            Estado.ESFERA -> {
                binding.txtRadio.visibility = View.VISIBLE
                habilitarBotonesCalc()
            }
            Estado.NO -> ocultarComponentes()
        }
    }

    private fun habilitarImagen() {
        binding.imgFigura.setImageResource(R.drawable.figuras)
    }

    private fun habilitarBotonesCalc() {
        binding.btnCalcArea.visibility = View.VISIBLE
        binding.btnCalcVol.visibility = View.VISIBLE
    }

    private fun actualizarSpinner() {
        when (viewModel.estadoActual) {
            Estado.CUBO -> {
                binding.spnFiguras.setSelection(0)
            }
            Estado.PRISMA -> {
                binding.spnFiguras.setSelection(1)
            }
            Estado.PIRAMIDE -> {
                binding.spnFiguras.setSelection(2)
            }
            Estado.CILINDRO -> {
                binding.spnFiguras.setSelection(3)
            }
            Estado.CONO -> {
                binding.spnFiguras.setSelection(4)
            }
            Estado.ESFERA -> {
                binding.spnFiguras.setSelection(5)
            }
            Estado.NO -> binding.spnFiguras.setSelection(0)
        }
    }

    private fun inicializarBotonesCalcArea() {
        binding.btnCalcArea.setOnClickListener {
            when (viewModel.estadoActual) {
                Estado.CUBO -> {
                    if (binding.txtLado.text.isNotEmpty()) {
                        var cubo = Cubo(binding.txtLado.text.toString().toDouble())
                        Snackbar.make(
                            binding.btnCalcArea,
                            "El área es ${cubo.calcularArea()}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            binding.btnCalcArea,
                            "Debe completar los campos para calcular el área.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                Estado.PRISMA -> {
                    if (binding.txtAreaBase.text.isNotEmpty() && binding.txtPerBase.text.isNotEmpty() && binding.txtAltura.text.isNotEmpty()) {
                        var prisma = Prisma(binding.txtAreaBase.text.toString().toDouble(), binding.txtPerBase.text.toString().toDouble(), binding.txtAltura.text.toString().toDouble())
                        Snackbar.make(
                            binding.btnCalcArea,
                            "El área del prisma es ${prisma.calcularArea()}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            binding.btnCalcArea,
                            "Debe completar los campos para calcular el área.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                Estado.PIRAMIDE -> {
                    if (binding.txtAreaBase.text.isNotEmpty() && binding.txtPerBase.text.isNotEmpty() && binding.txtApotema.text.isNotEmpty() && binding.txtAltura.text.isNotEmpty()) {
                        var piramide = Piramide(binding.txtAreaBase.text.toString().toDouble(), binding.txtPerBase.text.toString().toDouble(), binding.txtApotema.text.toString().toDouble(), binding.txtAltura.text.toString().toDouble())
                        Snackbar.make(
                            binding.btnCalcArea,
                            "El área del prisma es ${piramide.calcularArea()}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            binding.btnCalcArea,
                            "Debe completar los campos para calcular el área.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                Estado.CILINDRO -> {
                    if (binding.txtRadio.text.isNotEmpty() && binding.txtAltura.text.isNotEmpty()) {
                        var cilindro = Cilindro(binding.txtRadio.text.toString().toDouble(), binding.txtAltura.text.toString().toDouble())
                        Snackbar.make(
                            binding.btnCalcArea,
                            "El área del prisma es ${cilindro.calcularArea()}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            binding.btnCalcArea,
                            "Debe completar los campos para calcular el área.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                Estado.CONO -> {
                    if (binding.txtRadio.text.isNotEmpty() && binding.txtGener.text.isNotEmpty() && binding.txtAltura.text.isNotEmpty()) {
                        var cono = Cono(binding.txtRadio.text.toString().toDouble(), binding.txtGener.text.toString().toDouble(), binding.txtAltura.text.toString().toDouble())
                        Snackbar.make(
                            binding.btnCalcArea,
                            "El área del prisma es ${cono.calcularArea()}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            binding.btnCalcArea,
                            "Debe completar los campos para calcular el área.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                Estado.ESFERA -> {
                    if (binding.txtRadio.text.isNotEmpty()) {
                        var esfera = Esfera(binding.txtRadio.text.toString().toDouble())
                        Snackbar.make(
                            binding.btnCalcArea,
                            "El área del prisma es ${esfera.calcularArea()}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            binding.btnCalcArea,
                            "Debe completar los campos para calcular el área.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                Estado.NO -> binding.spnFiguras.setSelection(0)
            }
        }
    }

    private fun inicializarBotonesCalcVol() {
        binding.btnCalcVol.setOnClickListener {
            when (viewModel.estadoActual) {
                Estado.CUBO -> {
                    if (binding.txtLado.text.isNotEmpty()) {
                        var cubo = Cubo(binding.txtLado.text.toString().toDouble())
                        Snackbar.make(
                            binding.btnCalcArea,
                            "El volumen es ${cubo.calcularVolumen()}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            binding.btnCalcArea,
                            "Debe completar los campos para calcular el volumen.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                Estado.PRISMA -> {
                    if (binding.txtAreaBase.text.isNotEmpty() && binding.txtPerBase.text.isNotEmpty() && binding.txtAltura.text.isNotEmpty()) {
                        var prisma = Prisma(binding.txtAreaBase.text.toString().toDouble(), binding.txtPerBase.text.toString().toDouble(), binding.txtAltura.text.toString().toDouble())
                        Snackbar.make(
                            binding.btnCalcArea,
                            "El volumen del prisma es ${prisma.calcularVolumen()}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            binding.btnCalcArea,
                            "Debe completar los campos para calcular el volumen.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                Estado.PIRAMIDE -> {
                    if (binding.txtAreaBase.text.isNotEmpty() && binding.txtPerBase.text.isNotEmpty() && binding.txtApotema.text.isNotEmpty() && binding.txtAltura.text.isNotEmpty()) {
                        var piramide = Piramide(binding.txtAreaBase.text.toString().toDouble(), binding.txtPerBase.text.toString().toDouble(), binding.txtApotema.text.toString().toDouble(), binding.txtAltura.text.toString().toDouble())
                        Snackbar.make(
                            binding.btnCalcArea,
                            "El volumen del prisma es ${piramide.calcularVolumen()}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            binding.btnCalcArea,
                            "Debe completar los campos para calcular el volumen.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                Estado.CILINDRO -> {
                    if (binding.txtRadio.text.isNotEmpty() && binding.txtAltura.text.isNotEmpty()) {
                        var cilindro = Cilindro(binding.txtRadio.text.toString().toDouble(), binding.txtAltura.text.toString().toDouble())
                        Snackbar.make(
                            binding.btnCalcArea,
                            "El volumen del prisma es ${cilindro.calcularVolumen()}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            binding.btnCalcArea,
                            "Debe completar los campos para calcular el volumen.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                Estado.CONO -> {
                    if (binding.txtRadio.text.isNotEmpty() && binding.txtGener.text.isNotEmpty() && binding.txtAltura.text.isNotEmpty()) {
                        var cono = Cono(binding.txtRadio.text.toString().toDouble(), binding.txtGener.text.toString().toDouble(), binding.txtAltura.text.toString().toDouble())
                        Snackbar.make(
                            binding.btnCalcArea,
                            "El volumen del prisma es ${cono.calcularVolumen()}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            binding.btnCalcArea,
                            "Debe completar los campos para calcular el volumen.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                Estado.ESFERA -> {
                    if (binding.txtRadio.text.isNotEmpty()) {
                        var esfera = Esfera(binding.txtRadio.text.toString().toDouble())
                        Snackbar.make(
                            binding.btnCalcArea,
                            "El volumen del prisma es ${esfera.calcularVolumen()}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            binding.btnCalcArea,
                            "Debe completar los campos para calcular el volumen.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                Estado.NO -> binding.spnFiguras.setSelection(0)
            }
        }
    }

    private fun limpiarCampos() {
        binding.txtLado.text.clear()
        binding.txtAreaBase.text.clear()
        binding.txtPerBase.text.clear()
        binding.txtAltura.text.clear()
        binding.txtApotema.text.clear()
        binding.txtGener.text.clear()
        binding.txtRadio.text.clear()
    }
}