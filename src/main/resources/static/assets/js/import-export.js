const MANAGER_HIDDEN = 'manager-hidden';
const EXPORT_SHOWN = 'export-shown';
const IMPORT_SHOWN = 'import-shown';

const HIDDEN_CLASSES = [MANAGER_HIDDEN];
const SHOWN_CLASSES = [EXPORT_SHOWN, IMPORT_SHOWN];

const UA = 'UA';

const getCountry = (td, loadingType) => {
    return $($(td).find(`span.${loadingType}-country`)[0])
            .text()
            .toString()
            .toUpperCase();
};

const filtersObj = {
    my: hideOtherManagersTransportations,
    exportFilter: hideNotExport
};

const CHECKBOXES = 'div#table-filters input[type="checkbox"]';
const SELECTED_CHECKBOXES_IDS = new Set();
const FILTER_ID_TO_NAME = new Map([
    ['my-check', 'MY_CHECK'],
    ['export-check', 'EXPORT_CHECK'],
    ['import-check', 'IMPORT_CHECK'],
    ['ua-check', 'UA_CHECK'],
    ['eu-check', 'EU_CHECK'],
    ['wo-equipage', 'WO_EQUIPAGE_CHECK'],
    ['custom-filter', 'CUSTOM_FILTER']
]);

jQuery(document).ready(function () {
    addCommentEditor();
//    addCurrentUserFilter();
//    addExportFilter();
//    addImportFilter();
    $(CHECKBOXES).each(function () {
        console.log(`id ${this.id}`);
        
        this.addEventListener('click', function () {
//            for (let prop in filtersObj) {
//                filtersObj[prop]();
//            }

            $(CHECKBOXES).each(function () {
                if (this.checked) {
                    SELECTED_CHECKBOXES_IDS.add(this.id);
                } else {
                    SELECTED_CHECKBOXES_IDS.delete(this.id);
                }
            });
            console.log(`id = ${this.id} ${SELECTED_CHECKBOXES_IDS.has(this.id)} size ${SELECTED_CHECKBOXES_IDS.size}`);
            
            let selectedFilters = '';
            for (let id of SELECTED_CHECKBOXES_IDS) {
                selectedFilters += (selectedFilters ? ', ' : '') + id;
            }
            const url = `/import-export?filters=${selectedFilters}`;
            console.log(`url ${url}`);
            $('div#table-filters form').attr('action', url);
//            $('div#table-filters form button#select-filter-form-button').click();
            window.location.href = url;
        });
    });
    
//    $('div#table-filters').each(function () {
//        this.addEventListener('click', function () {
//            const selectedFilters = [];
//            for (let id of CHECKBOXES) {
//                const filter = FILTER_ID_TO_NAME.get(id);
//                console.log(`id ${id} ${FILTER_ID_TO_NAME.has(id)} filter ${filter}`);
//                if (filter) {
//                    selectedFilters.push(filter);
//                }
//            } 
//            console.log(`SELECTED_CHECKBOXES_IDS ${SELECTED_CHECKBOXES_IDS} selectedFilters ${selectedFilters}`);
//            window.location.href = '/import-export';
//        });
//    });
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
                            toggleParentTrVisibility(this, checkBoxState,
                                    MANAGER_HIDDEN);
                        }
                    });
        });
    });
}

function hideOtherManagersTransportations() {
    const filterDiv = $('div#current-user-filter');
    const currentUserId = parseInt($(filterDiv).find('p').text());
    const filterInput = $('div#current-user-filter input')[0];
    console.log(filterInput);
    const checkBoxState = filterInput.checked;
    console.log(`checkBoxState ${checkBoxState}`);
    $('div#transportations-div table tr td.manager-td p.manager-id')
            .each(function () {
                const managerId = parseInt($(this).text());
                if (currentUserId !== managerId) {
                    toggleParentTrVisibility(this, checkBoxState,
                            MANAGER_HIDDEN);
                }
            });

}

function addExportFilter() {
    const filterDiv = $('div#export-filter');
    const filterInput = $(filterDiv).find('input').each(function (n) {
        this.addEventListener('click', function (e) {
            const checkBoxState = this.checked;
            $('div#transportations-div table tr td.direction')
                    .each(function () {
                        const loadingCountry = getCountry(this, 'loading');
                        const unloadingCountry = getCountry(this, 'unloading');
                        console.log(`export ${loadingCountry} ${unloadingCountry} ${loadingCountry === UA && unloadingCountry !== UA}`);
                        if (loadingCountry === UA && unloadingCountry !== UA) {
                            checkBoxState ? showParentTr(this, EXPORT_SHOWN) : hideParentTr(this, EXPORT_SHOWN);
                        } else {
                            hideParentTr(this, EXPORT_SHOWN);
                        }
                    });
        });
    });
}

function hideNotExport() {
    const filterDiv = $('div#export-filter');
    const filterInput = $(filterDiv).find('input')[0];
    const checkBoxState = filterInput.checked;
    $('div#transportations-div table tr td.direction')
            .each(function () {
                const loadingCountry = getCountry(this, 'loading');
                const unloadingCountry = getCountry(this, 'unloading');
                console.log(`export ${loadingCountry} ${unloadingCountry} ${loadingCountry === UA && unloadingCountry !== UA}`);
                if (loadingCountry !== UA || unloadingCountry === UA) {
                    hideParentTr(this, EXPORT_SHOWN);
                } else {
                    showParentTr(this, EXPORT_SHOWN);
                }
            });
}

function hideNotImport() {
    const filterDiv = $('div#import-filter');
    const filterInput = $(filterDiv).find('input').each(function (n) {
        this.addEventListener('click', function (e) {
            const checkBoxState = this.checked;
            $('div#transportations-div table tr td.direction')
                    .each(function () {
                        const loadingCountry = getCountry(this, 'loading');
                        const unloadingCountry = getCountry(this, 'unloading');
                        console.log(`import loadingCountry ${loadingCountry} unloadingCountry ${unloadingCountry} ${loadingCountry !== UA && unloadingCountry === UA}`);
                        if (loadingCountry !== UA && unloadingCountry === UA) {
                            checkBoxState ? showParentTr(this, IMPORT_SHOWN) : hideParentTr(this, IMPORT_SHOWN);
                        } else {
                            hideParentTr(this, IMPORT_SHOWN);
                        }
                    });
        });
    });
}

function addImportFilter() {
    const filterDiv = $('div#import-filter');
    const filterInput = $(filterDiv).find('input').each(function (n) {
        this.addEventListener('click', function (e) {
            const checkBoxState = this.checked;
            $('div#transportations-div table tr td.direction')
                    .each(function () {
                        const loadingCountry = getCountry(this, 'loading');
                        const unloadingCountry = getCountry(this, 'unloading');
                        console.log(`import loadingCountry ${loadingCountry} unloadingCountry ${unloadingCountry} ${loadingCountry !== UA && unloadingCountry === UA}`);
                        if (loadingCountry !== UA && unloadingCountry === UA) {
                            checkBoxState ? showParentTr(this, IMPORT_SHOWN) : hideParentTr(this, IMPORT_SHOWN);
                        } else {
                            hideParentTr(this, IMPORT_SHOWN);
                        }
                    });
        });
    });
}

function toggleParentTrVisibility(td, checkBoxState, cls) {
    const parentTr = $(td).closest('tr');
    if (checkBoxState) {
        $(parentTr).attr('hidden', 'hidden');
        $(parentTr).addClass(cls);
    } else {
        $(parentTr).removeClass(cls);
        if (!HIDDEN_CLASSES.some((c) => $(parentTr).hasClass(c))) {
            $(parentTr).removeAttr('hidden');
        }
    }
}

function showParentTr(td, cls) {
    const parentTr = $(td).closest('tr');
    $(parentTr).addClass(cls);
    if (!HIDDEN_CLASSES.some((c) => $(parentTr).hasClass(c))) {
        $(parentTr).removeAttr('hidden');
    }
}

function hideParentTr(td, cls) {
    const parentTr = $(td).closest('tr');
    $(parentTr).removeClass(cls);
    console.log(`hideParentTr ${!SHOWN_CLASSES.some((c) => $(parentTr).hasClass(c))}`);
    if (!SHOWN_CLASSES.some((c) => $(parentTr).hasClass(c))) {
        $(parentTr).attr('hidden', 'hidden');
        $(parentTr).addClass(cls);
    }
}
