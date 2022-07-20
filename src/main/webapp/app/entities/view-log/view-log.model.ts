export interface IViewLog {
  id?: number;
  actionName?: string | null;
  entityName?: string | null;
  entityId?: string | null;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
}

export class ViewLog implements IViewLog {
  constructor(
    public id?: number,
    public actionName?: string | null,
    public entityName?: string | null,
    public entityId?: string | null,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date
  ) {}
}

export function getViewLogIdentifier(viewLog: IViewLog): number | undefined {
  return viewLog.id;
}
