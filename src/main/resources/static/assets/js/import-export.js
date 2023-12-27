const CHECKBOXES = 'div#table-filters input[type="checkbox"]';
const INPUTS = 'div#table-filters input[type="text"], div#table-filters input[type="date"], div#table-filters select';
const SELECTED_CHECKBOXES_IDS = new Set();

jQuery(document).ready(function () {
    addCommentEditor();
    addFiltering();
});

function addFiltering() {
    $(`${CHECKBOXES}, ${INPUTS}`).each(function () {

        this.addEventListener('change', function () {
            $(CHECKBOXES).each(function () {
                if (this.checked) {
                    SELECTED_CHECKBOXES_IDS.add(this.id);
                } else {
                    SELECTED_CHECKBOXES_IDS.delete(this.id);
                }
            });

            let selectedCheckboxes = '';
            for (let id of SELECTED_CHECKBOXES_IDS) {
                selectedCheckboxes += (selectedCheckboxes ? ', ' : '') + id;
            }

            let inputValues = '';
            $(INPUTS).each(function () {

                if (this.value) {
                    inputValues += (inputValues ? '&' : '')
                            + `${this.id}=${this.value}`;
                }
            });
            if (inputValues) {
                inputValues = '&' + inputValues;
            }
            const url = `/import-export?filters=${selectedCheckboxes}${inputValues}`;
            $('div#table-filters form').attr('action', url);
            window.location.href = url;
        });
    });
}

function addCommentEditor() {
    let tdWithComment = $('td.comment');
    tdWithComment.each(function (n) {
        this.addEventListener('click', function (e) {
            $(this).find('h6').attr('hidden', 'hidden');
            $(this).find('form').removeAttr('hidden');
        });
    });
}