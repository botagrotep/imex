const LOADING_FORM = 'div#loadings div.loading-form';
const REQUIRED_FIELDS = ['loading-no', 'loading-city', 'loading-date', 'loading-time'];
const NAME_PREFIX = 'loadings[';
const ID_PREFIX = 'loadings';
const SELECT_HTML = getSelectHtml();

jQuery(document).ready(function () {
    prepareLoadingEditor();
});

function prepareLoadingEditor() {
    $('div#loadings button[value="add-loading"]').each(function () {
        this.addEventListener('click', function () {
            $(LOADING_FORM).last().each(function () {
                $(this).clone().insertAfter(this).each(function () {
                    $('div.card-title', this).text('Завантаження');
                    $('input, select', this).each(function () {
                        const name = $(this).attr('name');
                        if (name.indexOf(NAME_PREFIX) < 0) {
                            return;
                        }
                        const index = parseInt(name.slice(NAME_PREFIX.length));
                        const suffix = name.slice(NAME_PREFIX.length + ('' + index).length);
                        const newName = NAME_PREFIX + (index + 1) + suffix;
                        $(this).attr('name', newName);

                        const id = $(this).attr('id');
                        const idSuffix = id.slice(ID_PREFIX.length + ('' + index).length);
                        const newId = ID_PREFIX + (index + 1) + idSuffix;
                        $(this).attr('id', newId);
                    });
                    $(`${LOADING_FORM} select.loading-type`).each(addChangeListener);
                });
            });
        });
    });

    $(`${LOADING_FORM} select.loading-type`).each(addChangeListener);
}

function addChangeListener() {
    this.addEventListener('change', function () {
        const selectedVal = $(this).val();
        $(this).closest('div.card-body').children('div.card-title')
                .text(loadingTypes.some((t) => t === selectedVal)
                        ? 'Завантаження'
                        : 'Розвантаження');
    });
}

function getSelectHtml() {    
    let selectHtml = '';
    for (const key in loadingTypesMap) {
        selectHtml += `<option value="${key}">${loadingTypesMap[key]}</option>\n`;
    }
    return selectHtml;
}