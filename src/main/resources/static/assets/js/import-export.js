const CHECKBOXES = 'div#table-filters input[type="checkbox"]';
const SELECTED_CHECKBOXES_IDS = new Set();

jQuery(document).ready(function () {
    addCommentEditor();
    addCheckboxesFiltering();
});

function addCheckboxesFiltering() {
    $(CHECKBOXES).each(function () {
        console.log(`id ${this.id}`);

        this.addEventListener('click', function () {
            $(CHECKBOXES).each(function () {
                if (this.checked) {
                    SELECTED_CHECKBOXES_IDS.add(this.id);
                } else {
                    SELECTED_CHECKBOXES_IDS.delete(this.id);
                }
            });

            let selectedFilters = '';
            for (let id of SELECTED_CHECKBOXES_IDS) {
                selectedFilters += (selectedFilters ? ', ' : '') + id;
            }
            const url = `/import-export?filters=${selectedFilters}`;
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