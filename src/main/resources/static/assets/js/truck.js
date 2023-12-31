jQuery(document).ready(function () {
    let radiusFilterInput = $('input#radius-filter')[0];
    radiusFilterInput.addEventListener('change', function (e) {
        const radiusFromFilter = this.value;
        $('table#trucks-list tr').each(function(n){
            if (!$('td', this).hasClass('distance')) {
                return;
            }
            const distance = Number.parseFloat($(this).find('td.distance a span').text());
            if (distance && radiusFromFilter && distance > radiusFromFilter) {
                $(this).attr('hidden', 'hidden');
            } else {
                $(this).removeAttr('hidden');
            }
        });
    });
});