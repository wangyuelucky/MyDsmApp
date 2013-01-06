
if (!Ipms.sampletree)
    Ext.namespace('Ipms.sampletree');

Ipms.finance.FinanceBakStore = Ext.extend(Ipms.data.XmlStore, {
    constructor: function (load_url, params) {
        Srims.finance.FinanceBakStore.superclass.constructor.call(this, new Srims.finance.FinanceBakXmlReader(), load_url, params);
    }
});
