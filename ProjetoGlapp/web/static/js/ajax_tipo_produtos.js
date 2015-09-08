/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    var tabelaObj = $("#tabela-tipo-produtos");
    
    $.get("recursos/tipoproduto/1")
            .success(function(dadosRecebidos){
                var linha = $("<tr>");
                var colunaId = $("<td>");
                var colunaDescricao = $("<td>");
                colunaId.html(dadosRecebidos.idTipoProduto);
                colunaDescricao.html(dadosRecebidos.descricao);
                linha.append(colunaId);
                linha.append(colunaDescricao);                
                tabelaObj.find("tbody").append(linha);
    });

});