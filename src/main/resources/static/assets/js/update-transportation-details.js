jQuery(document).ready(function () {
    openLoadingEditor();
});

function openLoadingEditor() {    
//    $('div#loadings div.loading-output input, div#loadings div.loading-output select').attr('disabled', 'disabled');
    $('div#loadings div.loading-output').each(function (n) {
        $('h5.ti-pencil', this).each(function (n1) {
            this.addEventListener('click', function (e) {
                console.log($(this).html());
                $(this).parent('div').find('div.loading-edit').removeAttr('hidden');
            });
        });
    });
}