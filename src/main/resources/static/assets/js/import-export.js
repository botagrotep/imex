const MANAGER_HIDDEN = 'manager-hidden';
const EXPORT_HIDDEN = 'manager-hidden';

const HIDDEN_CLASSES = [MANAGER_HIDDEN, EXPORT_HIDDEN];

jQuery(document).ready(function () {
    addCommentEditor();
    addCurrentUserFilter();
    addExportFilter();
});

function addCommentEditor() {
    let tdWithComment = $('td.comment');
    tdWithComment.each(function (n) {
        this.addEventListener('click', function (e) {
            $(this).find('h6').attr('hidden', 'hidden');
            $(this).find('form').removeAttr('hidden');
        });
    });
}

function addCurrentUserFilter() {
    const filterDiv = $('div#current-user-filter');
    const currentUserId = parseInt($(filterDiv).find('p').text());
    const filterInput = $(filterDiv).find('input').each(function (n) {
        this.addEventListener('click', function (e) {
            const checkBoxState = this.checked;
            $('div#transportations-div table tr td.manager-td p.manager-id')
                    .each(function () {
                        const managerId = parseInt($(this).text());
                        if (currentUserId !== managerId) {
                            const parentTr = $(this).closest('tr');
                            if (checkBoxState) {
                                $(parentTr).attr('hidden', 'hidden');
                                $(parentTr).addClass(MANAGER_HIDDEN);
                            } else {
                                $(parentTr).removeClass(MANAGER_HIDDEN);
                                if (!HIDDEN_CLASSES.some((cls) => $(parentTr).hasClass(cls))) {
                                    $(parentTr).removeAttr('hidden');
                                }
                            }
                        }
                    });
        });
    });
}

function addExportFilter() {
    const filterDiv = $('div#export-filter');
    const currentUserId = parseInt($(filterDiv).find('p').text());
    const filterInput = $(filterDiv).find('input').each(function (n) {
        this.addEventListener('click', function (e) {
            const checkBoxState = this.checked;
            $('div#transportations-div table tr td.direction span.unloading-country')
                    .each(function () {
                        const unloadingCountry = $(this).text().toString();
                        if (unloadingCountry.toUpperCase() === 'UA') {
                            const parentTr = $(this).closest('tr');
                            if (checkBoxState) {
                                $(parentTr).attr('hidden', 'hidden');
                                $(parentTr).addClass(EXPORT_HIDDEN);
                            } else {
                                $(parentTr).removeClass(EXPORT_HIDDEN);
                                if (!HIDDEN_CLASSES.some((cls) => $(parentTr).hasClass(cls))) {
                                    $(parentTr).removeAttr('hidden');
                                }
                            }
                        }
                    });
        });
    });
}