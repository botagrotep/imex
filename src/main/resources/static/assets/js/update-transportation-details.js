const LOADING_FORM = 'div#loadings div.loading-form';
const REQUIRED_FIELDS = ['loading-no', 'loading-city', 'loading-date', 'loading-time'];

jQuery(document).ready(function () {
    prepareLoadingEditor();
});

function prepareLoadingEditor() {
    $(LOADING_FORM).last().each(function () {
        console.log($(this).html());
        $('input[required], select[required]', this).removeAttr('required');
        $(this).hide();
    });

    $('div#loadings button[value="add-loading"]').each(function () {
        this.addEventListener('click', function () {
            $(this).attr('disabled', 'disabled');
            $(LOADING_FORM).last().show().each(function () {
                $('input, select', this).each(function () {
                    REQUIRED_FIELDS.forEach((c) => $(this).hasClass(c) && $(this).attr('required', true));
                });
            });
        });
    });
    
    $(`${LOADING_FORM} select.loading-type`).each(function () {
        this.addEventListener('change', function () {
            const selectedVal = $(this).val();
            $(this).closest('div.card-body').children('div.card-title')
                .text(loadingTypes.some((t) => t === selectedVal) 
                    ? 'Завантаження' 
                    : 'Розвантаження');
        });
    });
}