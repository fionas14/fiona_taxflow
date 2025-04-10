package com.fionasiregar0032.taxflow.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fionasiregar0032.taxflow.R
import com.fionasiregar0032.taxflow.ui.theme.TaxflowTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("PPN", "PPh")
    var selectOption by remember { mutableStateOf<String?>(null) }

    var hitung by remember { mutableStateOf(false) }

    var selectedPPN by remember { mutableStateOf("11%") }
    var ppnLainnya by remember { mutableStateOf("") }
    var inputHarga by remember { mutableStateOf("") }
    var totalPPN by remember { mutableDoubleStateOf(0.0) }

    var jumlahTanggungan by remember { mutableStateOf("") }
    var totalPenghasilan by remember { mutableStateOf("") }
    var iuranJHT by remember { mutableStateOf("0") }
    var iuranBPJS by remember { mutableStateOf("0") }
    var iuranZakat by remember { mutableStateOf("0") }

    val pekerjaanOptions = listOf("Pegawai Tetap", "Non-Pegawai")
    var selectedPekerjaan by remember { mutableStateOf(pekerjaanOptions[0]) }
    var pekerjaanExpanded by remember { mutableStateOf(false) }

    var penghasilanNeto by remember { mutableDoubleStateOf(0.0) }
    var penghasilanKenaPajak by remember { mutableDoubleStateOf(0.0) }
    var pajak by remember { mutableDoubleStateOf(0.0) }


    Scaffold(
        containerColor = Color(0xFFFFF5E1),
        topBar = {
            TopAppBar(
                title = {
                    Text("Tax Flow", fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF90EE90),
                    titleContentColor = Color.Black,
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp)
                .imePadding()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Mari menghitung Pajak!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        if (selectOption == null) {

        Image(
            painter = painterResource(id = R.drawable.pajakk),
            contentDescription = "Ilustrasi Pajak",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {

        TextField(
            value = stringResource(R.string.pilih_jenis_pajak),
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.jenis_pajak)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { item ->
        DropdownMenuItem(
            text = { Text(item) },
            onClick = {
                selectOption = item
                expanded = false
            }
        ) }
    }
}

        Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.popBackStack()
                },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF90EE90))// Hijau
                ) {
                    Text(stringResource(R.string.kembali), color = Color.White)
                }
                }else if (selectOption == "PPN") {

        Text("Masukkan harga", fontWeight = FontWeight.SemiBold)

             OutlinedTextField(
                 value = inputHarga,
                 onValueChange = { inputHarga = it },
                 label = { Text(stringResource(R.string.harga)) },
                 modifier = Modifier.fillMaxWidth()
             )

        Spacer(modifier = Modifier.height(16.dp))

         Text("Pilih Persentase PPN", fontWeight = FontWeight.Bold)
            listOf("11%", "10%" , "Lainnya").forEach { persen ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedPPN == persen,
                        onClick = { selectedPPN = persen }
                    )
                        Text(text = persen)
                    }

                }

         if (selectedPPN == "Lainnya") {
             OutlinedTextField(
                 value = ppnLainnya,
                 onValueChange = { ppnLainnya = it },
                 label = { Text(stringResource(R.string.pilih_persentase_ppn))},
                 modifier = Modifier.fillMaxWidth()
             )
         }

         Spacer(modifier = Modifier.height(24.dp))

         Button(
             onClick = {
                 val harga = inputHarga.toDoubleOrNull() ?: 0.0
                 val persentase = when (selectedPPN) {
                     "11%" -> 11.0
                     "10%" -> 10.0
                     "Lainnya" -> {
                 val inputPersen = ppnLainnya.toDoubleOrNull()
                      if (inputPersen == null || inputPersen < 0) {
                          totalPPN = 0.0
                          hitung = true
                          return@Button
                      } else {
                       inputPersen
                      }
                 }
                     else -> 0.0
                     }
                        totalPPN = harga * (persentase / 100)
                        hitung = true
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF90EE90))
                ) {
         Text(stringResource(R.string.hitung))
                }

         Button(
             onClick = {
                 selectOption = null
                 hitung = false },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF90EE90))// Hijau
                    ) {
                        Text(stringResource(R.string.kembali), color = Color.White)
                    }

         if(hitung) {

         Spacer(modifier = Modifier.height(16.dp))
         Text( stringResource(R.string.total_ppn, totalPPN.toInt()))
         }
         } else {

          if (selectOption == "PPh") {

              OutlinedTextField(
                  value = jumlahTanggungan,
                  onValueChange = { jumlahTanggungan = it },
                  label = { Text(stringResource(R.string.jumlah_tanggungan)) },
                  modifier = Modifier.fillMaxWidth()
              )

              OutlinedTextField(
                  value = totalPenghasilan,
                  onValueChange = { totalPenghasilan = it },
                  label = { Text(stringResource(R.string.total_penghasilan)) },
                  modifier = Modifier.fillMaxWidth()
              )

              OutlinedTextField(
                  value = iuranJHT,
                  onValueChange = { iuranJHT = it },
                  label = { Text(stringResource(R.string.iuran_jht)) },
                  modifier = Modifier.fillMaxWidth()
              )

              OutlinedTextField(
                  value = iuranBPJS,
                  onValueChange = { iuranBPJS = it },
                  label = { Text(stringResource(R.string.iuran_bpjs)) },
                  modifier = Modifier.fillMaxWidth()
              )
              OutlinedTextField(
                  value = iuranZakat,
                  onValueChange = { iuranZakat = it },
                  label = { Text(stringResource(R.string.iuran_zakat)) },
                  modifier = Modifier.fillMaxWidth()
              )

          Text("Status", fontWeight = FontWeight.Bold)

              ExposedDropdownMenuBox(
                  expanded = pekerjaanExpanded,
                  onExpandedChange = { pekerjaanExpanded = !pekerjaanExpanded }
              ) {
              TextField(
                  value = selectedPekerjaan,
                  onValueChange = {},
                  readOnly = true,
                  label = { Text(stringResource(R.string.pilih_pekerjaan)) },
                  trailingIcon = {
                      ExposedDropdownMenuDefaults.TrailingIcon(pekerjaanExpanded) },
                            modifier = Modifier.menuAnchor().fillMaxWidth()
              )
              ExposedDropdownMenu(
                  expanded = pekerjaanExpanded,
                  onDismissRequest = { pekerjaanExpanded = false }
              ) {
                  pekerjaanOptions.forEach { option ->
                      DropdownMenuItem(
                          text = { Text(option) },
                          onClick = {
                              selectedPekerjaan = option
                              pekerjaanExpanded = false
                                    }
                                )
                            }
                        }

                    }
                }
                    Spacer(modifier = Modifier.height(24.dp))

          Button(
              onClick = {
                  val penghasilan = totalPenghasilan.toDoubleOrNull() ?: 0.0
                  val jht = iuranJHT.toDoubleOrNull() ?: 0.0
                  val bpjs = iuranBPJS.toDoubleOrNull() ?: 0.0
                  val zakat = iuranZakat.toDoubleOrNull() ?: 0.0
                  val totalPotongan = jht + bpjs + zakat

                  val biayaJabatan = if (selectedPekerjaan == "Pegawai Tetap") {
                      minOf(penghasilan * 0.005, 6_000_000.0)
                  } else 0.0

                  penghasilanNeto =
                      if (selectedPekerjaan == "Pegawai Tetap") {
                  penghasilan - biayaJabatan - totalPotongan
                  } else {
                      penghasilan * 0.5 - totalPotongan
                            }

                  val jumlahTanggunganInt = jumlahTanggungan.toIntOrNull()?.coerceAtMost(3) ?: 0
                  val ptkp = 54_000_000 + (jumlahTanggunganInt * 4_500_000)
                  val pkp = (penghasilanNeto - ptkp).coerceAtLeast(0.0)

                  penghasilanKenaPajak = pkp
                  pajak = hitungPPh(pkp)
                  hitung = true },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF90EE90))

                    ) {
         Text(stringResource(R.string.hitung))
                    }

         Spacer(modifier = Modifier.height(10.dp))

         if (hitung) {
             Text(stringResource(R.string.penghasilan_neto, penghasilanNeto.toInt()))
             Text(stringResource(R.string.penghasilan_kena_pajak, penghasilanKenaPajak.toInt()))
             Text(stringResource(R.string.pajak, pajak.toInt()))

         Spacer(modifier = Modifier.height(8.dp))
         }

         Button(
             onClick = {
                 jumlahTanggungan = ""
                 totalPenghasilan = ""
                 iuranJHT =""
                 iuranBPJS = ""
                 iuranZakat = ""
                 selectedPekerjaan = pekerjaanOptions[0]
                 penghasilanNeto = 0.0
                 penghasilanKenaPajak = 0.0
                 pajak = 0.0
                 hitung = false
         },
             modifier = Modifier.fillMaxWidth(),
             colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF90EE90))
         ) {

         Text(stringResource(R.string.reset))
         }

         Spacer(modifier = Modifier.height(16.dp))

         Button(
              onClick = {
                  selectOption = null
                  hitung = false },
             modifier = Modifier.fillMaxWidth(),
             colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF90EE90))// Hijau
         ) {

         Text(stringResource(R.string.kembali), color = Color.White)
                    }
                }
            }
        }
    }

    fun hitungPPh(pkp: Double): Double {
        var sisa = pkp
        var pajak = 0.0

        val lapisan = listOf(
            60_000_000.0 to 0.05,
            190_000_000.0 to 0.15,
            250_000_000.0 to 0.25,
            Double.MAX_VALUE to 0.30
        )

        for ((batas, tarif) in lapisan) {
            if (sisa <= 0) break
            val kena = minOf(sisa, batas)
            pajak += kena * tarif
            sisa -= kena
        }
        return pajak
    }


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SecondScreenPreview() {
    TaxflowTheme {
        val navController = rememberNavController()
        SecondScreen(navController)

    }
}


